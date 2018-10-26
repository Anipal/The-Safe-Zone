import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SafezoneService {

  constructor(private http: Http) { }

  registerSafezone(safezone) {
  	let headers = new Headers();
  	headers.append('Content-type', 'application/json');
  	return this.http.post('http://safezones.azurewebsites.net/users/safezoneRegGov', safezone, {headers: headers})
  		.pipe(map(res=>res.json()));
  }

  safezoneData() {
  	return this.http.get('http://safezones.azurewebsites.net/users/getSafezoneData')
  		.pipe(map(res=>res.json()));
  }

  safezonePending() {
    return this.http.get('http://safezones.azurewebsites.net/users/getSafezonePending')
      .pipe(map(res=>res.json()));
  }

  removeSafezone(aadhaar) {
    let headers = new Headers();
    headers.append('Content-type', 'application/json');
    return this.http.post('http://safezones.azurewebsites.net/users/safezoneRemove', aadhaar, {headers: headers})
      .pipe(map(res=>res.json()));
  }

  approveSafezone(aadhaar) {
    let headers = new Headers();
    headers.append('Content-type', 'application/json');
    return this.http.post('http://safezones.azurewebsites.net/users/safezoneApprove', aadhaar, {headers: headers})
      .pipe(map(res=>res.json()));
  }

  editSafezone(safezone) {
    let headers = new Headers();
    headers.append('Content-type', 'application/json');
    return this.http.post('http://safezones.azurewebsites.net/users/editSafezone', safezone, {headers: headers})
      .pipe(map(res=>res.json()));
  }
}
