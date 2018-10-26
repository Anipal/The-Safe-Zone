const mongoose = require("mongoose");
const bcrypt = require("bcryptjs");
const config = require("../config/database");

const loginSchema = mongoose.Schema({
	email: {
		type: String,
		required: true
	},
	password: {
		type: String,
		required: true
	},
	access: {
		type: String,
		required: true
	}
});

const Login = module.exports = mongoose.model('Login', loginSchema);

module.exports.getUserByEmail = function(emails, callback) {
	const query = {email: emails.email, password: emails.password};
	Login.findOne(query, callback);
}

module.exports.addUser = function(newUser, callback) {
	// bcrypt.genSalt(10, (err, salt) => {
	// 	bcrypt.hash(newUser.password, salt, (err, hash) => {
	// 		if(err) throw err;
	// 		newUser.password = hash;
	// 		newUser.save(callback);
	// 	});
	// });
	newUser.save(callback);
}

module.exports.comparePassword = function(candidatePassword, hash, callback) {
	bcrypt.compare(candidatePassword, hash, (err, isMatch) => {
		if(err) throw err;
		callback(null, isMatch);
	});
}