import { HttpClient } from '@angular/common/http';
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
    return this.http.get<any>(url);
  }
}
