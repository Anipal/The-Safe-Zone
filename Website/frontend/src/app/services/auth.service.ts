import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: Http) { }

  registerUser(user) {
  	let headers = new Headers();
  	headers.append('Content-type', 'application/json');
  	return this.http.post('http://safezones.azurewebsites.net/users/register', user, {headers: headers})
  		.pipe(map(res => res.json()));
  }

  authenticateUser(user) {
  	let headers = new Headers();
  	headers.append('Content-type', 'application/json');
  	return this.http.post('http://safezones.azurewebsites.net/users/authenticate', user, {headers: headers})
  		.pipe(map(res =>res.json()));
  }

  UserData() {
    return this.http.get('http://safezones.azurewebsites.net/users/getUserData')
      .pipe(map(res=>res.json()));
  }

  VolunteerData() {
    return this.http.get('http://safezones.azurewebsites.net/users/getVolunteerData')
      .pipe(map(res=>res.json()));
  }
}
