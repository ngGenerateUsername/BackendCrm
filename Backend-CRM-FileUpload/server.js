var express = require('express');
const path = require('path');
const cors = require('cors');
const bodyParser = require('body-parser');
const multer = require('multer');

var app = express();
var port = process.env.PORT || 4000;

// enable CORS
app.use(cors());
// parse application/json
app.use(bodyParser.json());
// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }));

// serving static files
app.use('D:/Esprit/PFE/CRM/FrontEnd/src/assets/img/avatars', express.static('uploads'));

// handle storage using multer
var storage = multer.diskStorage({
	destination: function (req, file, cb) {
		cb(null, 'D:/Esprit/PFE/CRM/FrontEnd/src/assets/img/avatars');
	},
	filename: function (req, file, cb) {
		cb(null, `${file.fieldname}-${Date.now()}${path.extname(file.originalname)}`);
	}
});
var upload = multer({ storage: storage });

// handle single file upload
app.post('/uploadfile', upload.single('dataFile'), (req, res, next) => {
	const file = req.file;
	if (!file) {
		return res.status(400).send({ message: 'Please upload a file.' });
	}
	return res.send({ message: 'File uploaded successfully.', file });
});

// handle multiple file upload
app.post('/uploadmultifile', upload.array('dataFiles', 10), (req, res, next) => {
	const files = req.files;
	if (!files || (files && files.length === 0)) {
		return res.status(400).send({ message: 'Please upload a file.' });
	}
	return res.send({ message: 'File uploaded successfully.', files });
});

// request handlers
app.get('/', (req, res) => {
	res.send('Welcome to the Node.js Tutorial! - Clue Mediator');
});

app.listen(port, () => {
	console.log('Server started on: ' + port);
});
