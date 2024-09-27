import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './movie-details.component.html',
  styleUrl: './movie-details.component.css'
})
export class MovieDetailsComponent implements OnInit {
  movie : any;
  movieId : number = 0;
  isFavorite : boolean = false;
  userRating : number = 0;
  constructor(private movieService : MovieService,private route : ActivatedRoute){ }
  ngOnInit(): void {
      const movieId = +this.route.snapshot.paramMap.get('id')!;
      this.getMovieDetails(movieId);

  }
  getMovieDetails(id : number){
    this.movieService.getMovieDetails(id).subscribe(
      response => {this.movie = response;},
      error => {console.error('error fetching movie details',error);}
    );
  }
  submitRating(){

  }
  addToWatchLater(){

  }
  toggleFavorite(){

  }
}
