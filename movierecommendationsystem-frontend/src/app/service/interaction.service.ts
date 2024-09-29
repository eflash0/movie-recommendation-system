import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InteractionService {
  baseUrl = 'http://localhost:8082/interactions';

  constructor(private http :HttpClient) { }

  rateMovie(userId : number,movieId : number,rating : number) : Observable<any>{
    const url = `${this.baseUrl}/${userId}/${movieId}/rating`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    const params = new HttpParams().set('rating',rating.toString());
    return this.http.post<any>(url,{},{headers,params});
  }

  watchList(userId : number) : Observable<any>{
    const url = `${this.baseUrl}/${userId}/watchlist`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url,{ headers });
  }

  addToWatchList(userId: number, movieId: number): Observable<any> {
    const url = `${this.baseUrl}/${userId}/${movieId}/watchlist`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.post<any>(url, {}, { headers });
  }

  removeFromWatchList(userId: number, movieId: number): Observable<any> {
    const url = `${this.baseUrl}/${userId}/${movieId}/watchlist`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.delete<any>(url, { headers });
  }

  addToFavorite(userId: number, movieId: number): Observable<any> {
    const url = `${this.baseUrl}/${userId}/${movieId}/favorite`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.post<any>(url, {}, { headers });
  }

  removeFromFavorite(userId: number, movieId: number): Observable<any> {
    const url = `${this.baseUrl}/${userId}/${movieId}/favorite`;
    const headers = new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
    return this.http.delete<any>(url, { headers });
  }

  favorite(userId : number) : Observable<any>{
    const url = `${this.baseUrl}/${userId}/favorite`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url,{ headers });
  }
  
}
