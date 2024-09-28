import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { TrailerDialogComponent } from '../trailer-dialog/trailer-dialog.component';

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
  trailer : any;
  constructor(private movieService : MovieService,private route : ActivatedRoute,
    private dialog : MatDialog
  ){ }
  ngOnInit(): void {
      const movieId = +this.route.snapshot.paramMap.get('id')!;
      this.getMovieDetails(movieId);
      this.getMovieTrailer(movieId);

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

  toggleTrailer(){
    if(this.trailer){
      this.dialog.open(TrailerDialogComponent,{data : {trailerKey:this.trailer.key}});
    }
  }

  getMovieTrailer(id:number){
    this.movieService.getMovieTrailer(id).subscribe(
      response => {this.trailer = response;},
      error => {console.error('error fetching trailer',error);}
    );
  }
}
