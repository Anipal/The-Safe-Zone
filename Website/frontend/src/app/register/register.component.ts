import { Component, OnInit } from '@angular/core';
// import { ValidateService } from  '../services/validate.service';
import { AuthService } from  '../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

	name: String;
	aadhaar: String;
	phone: String;
	address: String;
  safezone: String;

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }

  onRegSubmit() {
  	console.log(this.name);
  	const user = {
  		name: this.name,
  		aadhaar: this.aadhaar,
  		phone: this.phone,
      address: this.address,
  		safezone: this.safezone
  	}

  	// if(!this.validateService.validateRegister(user)) {
  	// 	console.log("Please enter all fields");
  	// 	return false;
  	// }

  	this.authService.registerUser(user).subscribe(data => {
  		if(data.success) {
  			console.log('User registered');
  		}
  		else {
  			console.log("there was some error");
  		}
  	})

  }

}
