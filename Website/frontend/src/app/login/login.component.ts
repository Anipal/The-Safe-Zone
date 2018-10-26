import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	email: String;
	password: String;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
  	const user = {
  		email: this.email,
  		password: this.password
  	}
  
  	this.authService.authenticateUser(user).subscribe(data => {
  		if(data.success==true) {
				this.router.navigate(['dashboard']);
			}
			else {
				console.log('qw');
			}
  	})	
  }
  

}
