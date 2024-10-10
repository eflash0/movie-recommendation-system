import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TvShowService {
  private baseUrl = 'http://localhost:8082/tv-shows';

  constructor(private http:HttpClient) { }

  getPopularTvShows(page : number) : Observable<any>{
    const url = `${this.baseUrl}?page=${page}`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url);
  }

  getTvShowDetails(id:number) : Observable<any>{
    const url = `${this.baseUrl}/${id}`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url);
  }

  getTvShowTrailer(id:number) : Observable<any>{
    const url = `${this.baseUrl}/${id}/trailer`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url);
  }
}
