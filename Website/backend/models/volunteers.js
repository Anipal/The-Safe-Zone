const mongoose = require("mongoose");
const bcrypt = require("bcryptjs");
const config = require("../config/database");

const volunteerSchema = mongoose.Schema({
	name: {
		type: String,
		required: true
	},
	phone: {
		type: String,
		required: true
	},
	// email: {
	// 	type: String,
	// 	required: true
	// },
	// password: {
	// 	type: String,
	// 	required: true
	// },
	aadhaar: {
		type: String,
		required: true
	},
	role: {
		type: String,
		required: true
	},
	safezone: {
		type: String,
		required: true
	},
	volno: {
		type: String,
		required: true
	}
});

const Volunteer = module.exports = mongoose.model('Volunteer', volunteerSchema);

// module.exports.getUserById = function(id, callback) {
// 	User.findById(id, callback);
// }

// module.exports.getUserByUsername = function(username, callback) {
// 	const query = {username: username};
// 	User.findOne(query, callback);
// }

module.exports.addVolunteer = function(newVolunteer, callback) {
	// bcrypt.genSalt(10, (err, salt) => {
	// 	bcrypt.hash(newUser.password, salt, (err, hash) => {
	// 		if(err) throw err;
	// 		newUser.password = hash;
	// 		newUser.save(callback);
	// 	});
	// });
	newVolunteer.save(callback)
}

// module.exports.comparePassword = function(candidatePassword, hash, callback) {
// 	bcrypt.compare(candidatePassword, hash, (err, isMatch) => {
// 		if(err) throw err;
// 		callback(null, isMatch);
// 	});
// }