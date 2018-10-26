import { Component, OnInit } from '@angular/core';
import { SafezoneService } from '../services/safezone.service';

@Component({
  selector: 'app-safezone-reg',
  templateUrl: './safezone-reg.component.html',
  styleUrls: ['./safezone-reg.component.css']
})
export class SafezoneRegComponent implements OnInit {
	id: String;
	lat: String;
	lon: String;
	address: String;

  constructor(private safezoneService: SafezoneService) { }

  ngOnInit() {
  }

  onRegSubmit() {
  	const newSafezone = {
  		id: this.id,
  		lat: this.lat,
  		lon: this.lon,
  		address: this.address
  	}
  	console.log(newSafezone);

  	this.safezoneService.registerSafezone(newSafezone).subscribe(data => {
  		if(data.success) {
  			console.log('Safezone Registered');
  		}
  		else {
  			console.log('Safezone not registered');
  		}
  	})
  }

}
