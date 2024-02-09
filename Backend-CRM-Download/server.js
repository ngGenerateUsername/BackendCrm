const express = require('express');
const app = express();
const fs = require('fs');
const path = require('path');

const filesDirectory = path.join(__dirname, 'files', 'Contrats');
const downloadedFilesDirectory = path.join(__dirname, 'files', 'downloaded');

// Create the downloadedFilesDirectory if it doesn't exist
if (!fs.existsSync(downloadedFilesDirectory)) {
  fs.mkdirSync(downloadedFilesDirectory, { recursive: true });
}

app.get('/download/:filename', (req, res) => {
  const filename = req.params.filename;
  const filePath = path.join(filesDirectory, filename);

  // Check if the file exists
  if (!fs.existsSync(filePath)) {
    res.status(404).send('The requested file does not exist.');
    return;
  }

  // Download the file
  const destinationPath = path.join(downloadedFilesDirectory, filename);
  fs.copyFileSync(filePath, destinationPath);
  res.download(destinationPath, (err) => {
    if (err) {
      res.status(500).send('An error occurred while downloading the file.');
    }
    // Delete the downloaded file after sending
    fs.unlinkSync(destinationPath);
  });
});

const port = process.env.PORT || 3001;

app.listen(port, () => {
  console.log(`The microservice is running on port ${port}.`);
});
