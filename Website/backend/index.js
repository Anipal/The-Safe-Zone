const express = require("express");
const path = require("path");
const bodyParser = require("body-parser");
const cors = require("cors");
const passport = require("passport");
const mongoose = require("mongoose");
const config = require('./config/database');

mongoose.connect(config.database, {useNewUrlParser: true});

mongoose.connection.on("connected", () => {
	console.log("Connected to database" + config.database);
})

mongoose.connection.on("error", () => {
	console.log("DB error");
})

const app = express();

const users = require('./routes/users');

app.use(cors());

app.use(express.static(path.join(__dirname,'public')));

app.use(bodyParser.json());

app.use(passport.initialize());
app.use(passport.session());

require('./config/passport')(passport);

app.use('/users', users);

app.get('/', (req,res) => {
	res.end("Running");
})

app.get('/check', (req, res) => {
	res.json({success: true, msg: 'Working'});
})

const port = process.env.PORT || 3000;

app.listen(port, () => {
	console.log("Server started on port " + port);
})