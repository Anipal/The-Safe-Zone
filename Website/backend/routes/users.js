const express = require("express");
const router = express.Router();
const passport = require("passport");
const jwt = require("jsonwebtoken");
const config = require('../config/database');
const User = require('../models/users');
const Volunteer = require('../models/volunteers');
const Safezone = require('../models/safezone');
const Login = require('../models/login');

router.post('/register', (req,res,next) => {
	let newUser = new User({
		name: req.body.name,
		aadhaar: req.body.aadhaar,
		phone: req.body.phone,
		address: req.body.address,
		safezone: req.body.safezone
	});

	User.addUser(newUser, (err, user) => {
		if(err) {
			res.json({success: false, msg: "Error in user reg"});
		}
		else {
			res.json({success: true, msg: "User registered successfully"})
		}
	});
});

router.post('/registerBody', (req, res, next) => {
	let newLogin = new Login({
		email: req.body.email,
		password: req.body.password,
		access: req.body.access
	});

	Login.addUser(newLogin, (err, user) => {
		if(err) {
			res.json({success: false, msg: "Error in user reg"});
		}
		else {
			res.json({success: true, msg: "User registered successfully"})
		}
	})
})

router.post('/authenticate', (req,res,next) => {
	const email = req.body.email;
	const password = req.body.password;

	const newObj = {
		email: email,
		password: password
	}
	Login.getUserByEmail(newObj, (err, user) => {
		if(err) throw err;
		if(!user) {
			return res.json({success: false, msg: 'User not Found'});
		}
		else {
			return res.json({success: true, msg: 'Logged In'});
		}
	});
});

router.get('/profile', passport.authenticate('jwt', {session: false}), (req,res,next) => {
	res.json({user: req.user});
});

router.post('/volreg', (req,res,next) => {
	console.log(req.body);
	var max;
	Volunteer.count({}, (err, count) => {
		max = count + 636;
		volid = 'Vol - ' + max
		let newVolunteer = new Volunteer({
			name: req.body.name,
			phone: req.body.phone,
			aadhaar: req.body.aadhaar,
			role: req.body.role,
			safezone: req.body.safezone,
			volno: volid
		});
		Volunteer.addVolunteer(newVolunteer, (err, volunteer) => {
			if(err) {
				res.json({success: false, msg: 'Error with volunteer registration', id: 0});
			}
			else {
				res.json({success: true, msg: 'Register Ho gaya', id: volid});
			}
		});
	})
});

router.get('/getUserData', (req,res,next) => {
	User.find({}, function(err, obj) {
		if(err) {
			res.json({success: false})
		}
		else {
			res.json({success: true, data: obj});	
		}
	})
})

router.get('/getVolunteerData', (req,res,next) => {
	Volunteer.find({}, function(err, obj) {
		if(err) {
			res.json({success: false})
		}
		else {
			res.json({success: true, data: obj});	
		}
	})
})

router.post('/safezonereg', (req,res,next) => {
	let newSafezone = new Safezone({
		lat: req.body.lat,
		lon: req.body.lon,
		address: req.body.address,
		approved: false,
		// approved: req.body.approved,
		aadhaar: req.body.aadhaar,
		email: req.body.email,
		phone: req.body.phone,
		name: req.body.name,
		food: 0,
		meds: 0,
		water: 0,
		sanitation: 0,
		capacity: 0
	});

	Safezone.addSafezone(newSafezone, (err, safezone) => {
		if(err) {
			res.json({success: false, msg: 'Error with registering safezone'});
		}
		else {
			res.json({success: true, msg: 'Safezone Registered'});
		}
	});
});

router.post('/safezoneRegGov', (req,res,next) => {
	console.log(req.body);
	let newSafezone = new Safezone({
		id: req.body.id,
		lat: req.body.lat,
		lon: req.body.lon,
		address: req.body.address,
		approved: true,
		food: 0,
		meds: 0,
		water: 0,
		sanitation: 0,
		capacity: 0
	});
	Safezone.addSafezone(newSafezone, (err, safezone) => {
		if(err) {
			res.json({success: false, msg: 'Error with safezone reg'});
		}
		else {
			res.json({success: true, msg: 'Safezone Registered'});
		}
	})
})

router.get('/getSafezones', (req,res,next) => {
	var coords = [];
	i=0;
	Safezone.find({}, function(err, obj) {
		var len = obj.length;
		var flag=1;
		var count =0;
		while(flag) {
			if(i<len) {
				if(obj[i].approved=="true") {
					coords.push([]);
					coords[count].push(obj[i].id);
					coords[count].push(obj[i].lat);
					coords[count].push(obj[i].lon);
					coords[count].push(obj[i].capacity);
					count+=1;
					// coords[count].push(obj[i].lat);
				}
				i++;
			}
			else {
				res.json({"coords": coords});
				flag=0;
			}
		}
	})
})

router.get('/getSafezoneData', (req,res,next) => {
	Safezone.find({approved: true}, function(err, obj) {
		if(err) {
			res.json({success: false})
		}
		else {
			res.json({success: true, data: obj});	
		}
	})
})

router.get('/getSafezonePending', (req,res,next) => {
	Safezone.find({approved: false}, function(err, obj) {
		if(err) {
			res.json({success: false})
		}
		else {
			res.json({success: true, data: obj});	
		}
	})
})

router.post('/getDetails', (req, res, next) => {
	const aadhaar = req.body.aadhaar;
	const phone = req.body.phone;
	const name = req.body.name;
	if(aadhaar!=undefined) {
		const query = {aadhaar: aadhaar};
		User.findOne(query, (err, obj) => {
			if(err) {
				res.json({success: false, msg: 'There was some error'})
			}
			else {
				if(obj) {
					Safezone.find({id: obj.safezone}, (errr,det) => {
						if(errr) {
							res.json({success: false, msg: 'Some error'})
						}
						else {
							res.json({success: true, msg: 'Users found', obj: obj, lat: det[0].lat, lon: det[0].lon});
						}
					})
				}
				else {
					res.json({success: true, msg: 'No user exists'})
				}
			}
		});
	}
	else if(phone!=undefined) {
		const query = {phone: phone};
		User.findOne(query, (err, obj) => {
			if(err) {
				res.json({success: false, msg: 'There was some error'})
			}
			else {
				if(obj) {
					Safezone.find({id: obj.safezone}, (errr,det) => {
						if(errr) {
							res.json({success: false, msg: 'Some error'})
						}
						else {
							res.json({success: true, msg: 'Users found', obj: obj, lat: det[0].lat, lon: det[0].lon});
						}
					})
				}
				else {
					res.json({success: true, msg: 'No user exists'})
				}
			}
		})
	}
	else {
		const query = {name: name};
		User.find(query, (err, obj) => {
			if(err) {
				res.json({success: false, msg: 'There was some error'});
			}
			else {
				if(obj) {
					Safezone.find({id: obj.safezone}, (errr,det) => {
						if(errr) {
							res.json({success: false, msg: 'Some error'})
						}
						else {
							res.json({success: true, msg: 'Users found', obj: obj, lat: det[0].lat, lon: det[0].lon});
						}
					})
				}
				else {
					res.json({success: true, msg: 'No user exists'});
				}
			}
		})
	}
})

router.post('/safezoneRemove', (req,res,next) => {
	const aadhaarDet = req.body.aadhaar;
	Safezone.remove({aadhaar: aadhaarDet}, (err, obj) => {
		if(err) {
			res.json({success: false});
		}
		else {
			if(obj.n==1) {
				res.json({success: true, msg: 'Safezone removed'});
			}
			else {
				res.json({success: false, msg: 'Safezone does not exist'});
			}
		}
	})
})

router.post('/safezoneApprove', (req, res, next) => {
	const aadhaar = req.body.aadhaar;
	const id = req.body.id;
	Safezone.update(
		{"aadhaar": aadhaar},
		{$set: {"approved": true, "id": id}},
		(err, obj) => {
			if(err) {
				res.json({success: false})
			}
			else {
				res.json({success: true, obj: obj})
			}
		});
})

router.post('/editSafezone', (req, res, next) => {
	const id = req.body.id;
	const capacity = req.body.capacity;
	const food = req.body.food;
	const meds = req.body.meds;
	const water = req.body.water;
	const sanitation = req.body.sanitation;
	Safezone.update(
		{"id": id},
		{$set: {"capacity": capacity, "food": food, "water": water, "meds": meds,"sanitation": sanitation}},
		(err, obj) => {
			if(err) {
				res.json({success: false})
			}
			else {
				res.json({success: true, obj: obj})
			}
		}
	)
})

module.exports = router;