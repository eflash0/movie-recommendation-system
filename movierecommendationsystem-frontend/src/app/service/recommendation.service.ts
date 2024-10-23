import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecommendationService {
  baseUrl = 'http://localhost:8082/recommendation';
  
  constructor(private http:HttpClient) { }

  train() : Observable<any>{
    const url = `${this.baseUrl}/train`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`);
    return this.http.post(url,{},{ headers });
  }

  getRecommendations(userId : number) : Observable<any>{
    const url = `${this.baseUrl}/get-recommendations`;
    const params = new HttpParams().set('userId',userId);
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`);
    return this.http.post(url,{},{ headers,params });
  }
}
