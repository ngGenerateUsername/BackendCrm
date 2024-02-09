const express = require("express");
const cors = require("cors");
const path = require("path");
const app = express();
const converter = require('json-2-csv');
const fs = require('fs');
const csvRoutes = require("./routes/csvRoutes");
var bodyParser = require('body-parser');
var multer = require('multer');
var xlstojson = require("xls-to-json-lc");
var xlsxtojson = require("xlsx-to-json-lc");
var json2xls = require("json2xls");
app.use(cors());
app.use(express.json());
app.use(bodyParser.json());

app.use("/api", csvRoutes);
app.use("/public", express.static(path.join(__dirname, "public")));

app.post("/api/JsonToExel", async (req, res) => {
  try {
    console.log(req.body);
    var xls = json2xls(req.body);

      var date = new Date();
      var month = date.getMonth() + 1;
      month = (month < 10 ? '0' : '') + month;
      var day = date.getDate();
      day = (day < 10 ? '0' : '') + day;
      var filename = date.getFullYear() + '-' + month + '-' + day + 'T' + date.getHours() + '-' + date.getMinutes() + '-' + date.getSeconds();
      fs.writeFileSync("../files/csv/"+filename+'.xlsx', xls,'binary');
      res
    .status(200)
    .download("../files/csv/"+filename+'.xlsx');
  } catch (error) {
      console.log(error);
    }
});

app.post("/api/JsonToCsv", async (req, res) => {
  try {
    console.log(req.body);
    converter.json2csv(req.body, (err, csv) => {
      if (err) {throw err;}
      console.log(csv);

      var date = new Date();
      var month = date.getMonth() + 1;
      month = (month < 10 ? '0' : '') + month;
      var day = date.getDate();
      day = (day < 10 ? '0' : '') + day;
      var filename = date.getFullYear() + '-' + month + '-' + day + 'T' + date.getHours() + '-' + date.getMinutes() + '-' + date.getSeconds();
      fs.writeFileSync("../files/csv/"+filename+'.csv', csv);
    res
    .status(200)
    .download("../files/csv/"+filename+'.csv');
       });

  } catch (error) {
      console.log(error);
      res.status(400).send({ msg: "error", error });
    }
});

var storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, "./public/csv")
  },
  filename: function (req, file, cb) {
    var datetimestamp = Date.now();
    cb(null, file.fieldname + '-' + datetimestamp + '.' + file.originalname.split('.')[file.originalname.split('.').length -1])
  }
});

var upload = multer({ //multer settings
  storage: storage,
  fileFilter : function(req, file, callback) { //file filter
    if (['xls', 'xlsx'].indexOf(file.originalname.split('.')[file.originalname.split('.').length-1]) === -1) {
      return callback(new Error('Wrong extension type'));
    }
    callback(null, true);
  }
}).single('file');

app.post('/api/ExelToJson', function(req, res) {
  var exceltojson;
  upload(req,res,function(err){
    if(err){
      res.json({error_code:1,err_desc:err});
      return;
    }
    if(!req.file){
      res.json({error_code:1,err_desc:"No file passed"});
      return;
    }
    if(req.file.originalname.split('.')[req.file.originalname.split('.').length-1] === 'xlsx'){
      exceltojson = xlsxtojson;
    } else {
      exceltojson = xlstojson;
    }
    console.log(req.file.path);
    try {
      exceltojson({
        input: req.file.path,
        output: null, //since we don't need output.json
        lowerCaseHeaders:true
      }, function(err,result){
        if(err) {
          return res.json({error_code:1,err_desc:err, data: null});
        }
        res.json(result);
      });
    } catch (e){
      res.json({error_code:1,err_desc:"Corupted excel file"});
    }
  })
});
app.listen(4001, () => {
  console.log("App is running on PORT 4001");
});
