import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
	user: any;
  constructor(private authService: AuthService) { }

  ngOnInit() {
  	this.authService.UserData().subscribe(datas => {
  		// console.log(datas.data);
  		this.user = datas.data;
  	})
  }


}
