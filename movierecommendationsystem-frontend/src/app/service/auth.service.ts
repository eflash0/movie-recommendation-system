import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl = 'http://localhost:8082/auth';
  constructor(private http : HttpClient) { }

  login(loginRequest : any) : Observable<any>{
    const url = `${this.baseUrl}/login`;
    return this.http.post<any>(url,loginRequest);
  }
}
