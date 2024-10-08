import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private baseUrl = 'http://localhost:8082/movies';

  constructor(private http:HttpClient) { }

  getPopularMovies(page : number) : Observable<any>{
    const url = `${this.baseUrl}?page=${page}`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url);
  }

  getMovieDetails(id:number) : Observable<any>{
    const url = `${this.baseUrl}/${id}`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url);
  }

  getMovieTrailer(id:number) : Observable<any>{
    const url = `${this.baseUrl}/${id}/trailer`;
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url);
  }

  search(query : string, page : number) : Observable<any>{
    const url = `${this.baseUrl}/search`;
    const params = new HttpParams().set('query',query).set('page',page.toString());
    const headers = new HttpHeaders().set('Authorization',`Bearer ${localStorage.getItem('token')}`)
    return this.http.get<any>(url,{ params });
  }
}
