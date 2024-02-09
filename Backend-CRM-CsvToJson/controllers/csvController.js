const fs = require('fs');
const path = require('path');
const csv = require('fast-csv');
const converter = require('json-2-csv');
exports.create = async (req, res) => {
  console.log(req.file);
  const totalRecords = [];
try{
console.log(path.join(__dirname, '../', '/public/csv/' + req.file.filename))
  fs.createReadStream(path.join(__dirname, '../', '/public/csv/' + req.file.filename))
    .pipe(csv.parse({ headers: true }))
    .on('error', error => console.error(error))
    .on('data', row => totalRecords.push(row))
    .on('end', async rowCount => {
      try{
        
        res.json(totalRecords);
      }catch(err){
        res.status(400).json(err);
      }
    });

  }catch(error){
    res.status(400).json(error)
  }
};

exports.jsontocsv = async (req, res) => {
  console.log(req.data);
  const todos = [{
    "id": 1,
    "title": "delectus aut autem",
    "completed": false
},
{
    "id": 2,
    "title": "quis ut nam facilis et officia qui",
    "completed": false
},
{
    "id": 3,
    "title": "fugiat veniam minus",
    "completed": false
}
];
try{
  converter.json2csv(todos, (err, csv) => {
    if (err) {
        throw err;
    }

    // print CSV string
    console.log(csv);

    // write CSV to a file
    fs.writeFileSync('todos.csv', csv);
    
});

  }catch(error){
    res.status(400).json(error)
  }
};