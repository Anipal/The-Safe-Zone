import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-volunteers',
  templateUrl: './volunteers.component.html',
  styleUrls: ['./volunteers.component.css']
})
export class VolunteersComponent implements OnInit {
	volunteers: any;
  constructor(private authService: AuthService) { }

  ngOnInit() {
  	this.authService.VolunteerData().subscribe(datas => {
  		this.volunteers = datas.data;
  	})
  }

}
