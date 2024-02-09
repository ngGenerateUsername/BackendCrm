var nodemailer = require('nodemailer');
const express = require("express");
const app = express();
const cors = require("cors");
app.use(cors());
app.use(express.json());
app.use(
  cors({
    origin: "*",
  })
);
app.use(express.json());
app.use(express.json());
async function SendMail(content) {
    // Create a SMTP transporter object
    let transporter = nodemailer.createTransport({
      service: "gmail",
      auth: {
        user: "hossemmm999@gmail.com",
        pass: "inkcubqegasazkxj",
      },
      tls: {
        rejectUnauthorized: false,
      },
    });
  
    // Message object
    let message = {
      from: "hossemmm999@gmail.com",
      to: content.to,
      // Subject of the message
      subject:content.subject,
      // plaintext body
       text: content.text,
      html: content.html,
    };
  
    await transporter.sendMail(message, (error, success) => {
      if (error) {
        console.log(error);
      } else {
        console.log("Email sent successfully!");
      }
    });
    transporter.close();
  }


app.post("/sendMail", async (req, res) => {
    try {
     // const { to } = req.body.to;
      //const { subject } = req.body.subject;
      //const { text } = req.body.text;
      const content =  req.body;
      SendMail(content);
      res
      .status(200)
      .send({ msg: "pass" });
    } catch (error) {
        console.log(error);
        res.status(400).send({ msg: "error", error });
      }
  });

  app.listen(9090, (err) => {
    err ? console.log(err) : console.log(`Server is Running on PORT 9090`);
  });

  
