import { Component, OnInit } from '@angular/core';
import { SafezoneService } from '../services/safezone.service';

@Component({
  selector: 'app-safezones',
  templateUrl: './safezones.component.html',
  styleUrls: ['./safezones.component.css']
})
export class SafezonesComponent implements OnInit {
	safeId: String;
	approve: any = false;
	safezone: any;
	edit :any = false;

	capacity: any;
	food: any;
	water: any;
	meds: any;
	sanitation: any;
  constructor(private safezoneService: SafezoneService) { }
  safezones: any;
  pending: any;
  ngOnInit() {
  	this.safezoneService.safezoneData().subscribe(datas => {
  		this.safezones = datas.data;
  	})

  	this.safezoneService.safezonePending().subscribe(datas => {
  		this.pending = datas.data;
  	})
  }

  onReject(i) {
  	// console.log(this.pending[i].aadhaar);
  	const safezone = {
  		aadhaar: this.pending[i].aadhaar,
  		id: 12
  	}
  	console.log(safezone);
  	this.safezoneService.removeSafezone(safezone).subscribe(data => {
  		if(data.success) {
			  console.log('Removed');
			  location.reload();
  		}
  		else {
  			console.log('Some error');
  		}
  	})
  }

  onApprove(i) {
  	this.approve=true;
  	this.safezone = {
  		aadhaar: this.pending[i].aadhaar,
  		id: 'A'
  	}
  }
  onSubmit() {
  	this.safezone.id = this.safeId;
  	console.log(this.safezone);
  	this.safezoneService.approveSafezone(this.safezone).subscribe(data => {
  		if(data.success) {
			  console.log('Approved');
			  location.reload();
  		}
  		else {
  			console.log('Some error');
  		}
  	})
  }

  onEdit(i) {
  	this.edit=true;
  	this.safezone = {
  		id: this.safezones[i].id,
  		capacity: 0,
  		food: 0,
  		water: 0,
		  meds: 0,
		  sanitation: 0
  	}
  }

  onEditSubmit() {
  	this.safezone.capacity = this.capacity;
  	this.safezone.food = this.food;
  	this.safezone.water = this.water;
	  this.safezone.meds = this.meds;
	  this.safezone.sanitation = this.sanitation;
  	console.log(this.safezone);
  	this.safezoneService.editSafezone(this.safezone).subscribe(data => {
  		if(data.success) {
			  console.log('Approved');
			  location.reload();
  		}
  		else {
  			console.log('Some error');
  		}
	  })
  }
}
