import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../service/movie.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-movies',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './get-movies.component.html',
  styleUrl: './get-movies.component.css'
})
export class GetMoviesComponent implements OnInit {
  movies : any = [];
  page : number = 1;
  loading: boolean = false;
  constructor(private movieService : MovieService){ }
  ngOnInit(): void {
      this.loadMovies();
  }
  loadMovies(){
    this.movieService.getPopularMovies(this.page).subscribe(
      response => {this.movies.push(...response)},
      error => {console.error('error fetching movies',error);}
    );
  }
}
