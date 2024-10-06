import { HttpClient, HttpParams } from '@angular/common/http';
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

  oauth2Login(code : string) : Observable<any>{
    const url = `${this.baseUrl}/login/oauth2/success`;
    const params = new HttpParams().set('code',code);
    return this.http.get<any>(url,{ params });
  }

  validateToken(token : string) : Observable<any>{
    const url = `${this.baseUrl}/validate-token?token=${token}`;
    return this.http.get<boolean>(url);
  }
}
