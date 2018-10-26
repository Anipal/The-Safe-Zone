const mongoose = require('mongoose');
const config = require('../config/database');

const safezoneSchema = mongoose.Schema({
	id: {
		type: String
	},
	lat: {
		type: String,
		required: true
	},
	lon: {
		type: String,
		required: true
	},
	address: {
		type: String,
		required: true
	},
	food: {
		type: String
	},
	meds: {
		type: String
	},
	water: {
		type: String
	},
	capacity: {
		type: String
	},
	sanitation: {
		type: String
	},
	notes: {
		type: String
	},
	approved: {
		type: String,
		required: true
	},
	aadhaar: {
		type: String
	},
	email: {
		type: String
	},
	phone: {
		type: String
	},
	name: {
		type: String
	}
})

const Safezone = module.exports = mongoose.model('Safezone', safezoneSchema);	

module.exports.addSafezone = function(newSafezone, callback) {
	newSafezone.save(callback);
}