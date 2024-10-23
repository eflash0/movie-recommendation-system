import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InteractionService {
  baseUrl = 'http://localhost:8082/interactions';

  constructor(private http :HttpClient) { }

  rate(userId : number,mediaId : number,rating : number,type : string) : Observable<any>{
    const url = `${this.baseUrl}/${userId}/${mediaId}/rating`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    const params = new HttpParams().set('rating',rating.toString()).set('type',type);
    return this.http.post<any>(url,{},{headers,params});
  }

  watchList(userId : number) : Observable<any>{
    const url = `${this.baseUrl}/${userId}/watchlist`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url,{ headers });
  }

  addToWatchList(userId: number, mediaId: number, type : string): Observable<any> {
    const url = `${this.baseUrl}/${userId}/${mediaId}/watchlist?type=${type}`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.post<any>(url, {}, { headers });
  }

  removeFromWatchList(userId: number, mediaId: number): Observable<any> {
    const url = `${this.baseUrl}/${userId}/${mediaId}/watchlist`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.delete<any>(url, { headers });
  }

  addToFavorite(userId : number, mediaId : number, type : string): Observable<any> {
    const url = `${this.baseUrl}/${userId}/${mediaId}/favorite?type=${type}`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.post<any>(url, {}, { headers });
  }

  removeFromFavorite(userId: number, mediaId: number): Observable<any> {
    const url = `${this.baseUrl}/${userId}/${mediaId}/favorite`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.delete<any>(url, { headers });
  }

  favorite(userId : number) : Observable<any>{
    const url = `${this.baseUrl}/${userId}/favorite`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url,{ headers });
  }

  findInteraction(userId: number, mediaId: number) : Observable<any>{
    const url = `${this.baseUrl}/${userId}/${mediaId}`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url,{ headers });
  }
  
}
