import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = 'http://localhost:8082/users';
  constructor(private http : HttpClient) { }

  register(registrationRequest : any) : Observable<any>{
    const url = `${this.baseUrl}/signup`;
    return this.http.post<any>(url,registrationRequest);
  }

  findByUsename(username : string) : Observable<any>{
    const url = `${this.baseUrl}/findByUsername?username=${username}`;
    return this.http.get<any>(url);    
  }

  findByEmail(email : string) : Observable<any>{
    const url = `${this.baseUrl}/findByEmail?email=${email}`;
    return this.http.get<any>(url);    
  }
}
