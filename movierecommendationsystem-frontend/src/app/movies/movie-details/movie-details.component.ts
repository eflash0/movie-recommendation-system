import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { TrailerDialogComponent } from '../trailer-dialog/trailer-dialog.component';
import { InteractionService } from '../../service/interaction.service';
import { NavigationBarComponent } from "../../navigation-bar/navigation-bar.component";
import { AuthService } from '../../service/auth.service';
import { UserService } from '../../service/user.service';
import { FooterComponent } from "../../footer/footer.component";

@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [CommonModule, FormsModule, NavigationBarComponent, FooterComponent],
  templateUrl: './movie-details.component.html',
  styleUrl: './movie-details.component.css'
})
export class MovieDetailsComponent implements OnInit {
  movie : any;
  movieId : number = 0;
  isFavorite : boolean = false;
  isWatchList : boolean = false;
  userRating : number = 0;
  trailer : any;
  username : string = '';
  user : any;
  interaction : any;
  constructor(private movieService : MovieService,private route : ActivatedRoute,
    private dialog : MatDialog,private interactionService : InteractionService,
    private authService : AuthService, private userService : UserService
  ){ }
  ngOnInit(): void {
    this.movieId = +this.route.snapshot.paramMap.get('id')!;
    this.getMovieDetails(this.movieId);
    this.getMovieTrailer(this.movieId);
    this.authService.extractUsernameFromToken().subscribe(
      response => {
        this.username = response.username;
        this.userService.findByUsename(this.username).subscribe(
          response => {this.user = response;
            this.interactionService.findInteraction(this.user.userId,this.movieId).subscribe(
              response => {this.interaction = response;
                this.userRating = response.rating;
                this.isFavorite = this.interaction.favorite;
                this.isWatchList = this.interaction.watchList;
              },
              error => {console.error('error fetching interaction',error);}
            );
          },
          error => {console.error('error fetching user',error);}
        );
      },
      error => {console.error('error extracting username',error);}
    );
  }

  getMovieDetails(id : number){
    this.movieService.getMovieDetails(id).subscribe(
      response => {this.movie = response;},
      error => {console.error('error fetching movie details',error);}
    );
  }

  rateMovie(){
    this.interactionService.rateMovie(this.user.userId,this.movieId,this.userRating).subscribe(
      response => {this.ngOnInit()},
      error => {console.error('error rating the movie',error);}
    );
  }

  addToWatchList(){    
    this.interactionService.addToWatchList(this.user.userId,this.movieId).subscribe(
      response => {console.log('added to watch list successfully',this.user);
      },
      error => {console.error('error adding movie to watch list',error);}
    );
  }

  removeFromWatchList(){
    this.interactionService.removeFromWatchList(this.user.userId,this.movieId).subscribe(
      response => {console.log('removed from watch list successfully',this.user);
      },
      error => {console.error('error removing movie from watch list',error);}
    );
  }

  addToFavorite(){
    this.interactionService.addToFavorite(this.user.userId,this.movieId).subscribe(
      response => {console.log('added to favorite successfully',response);
      },
      error => {console.error('error adding movie to favorite',error);}
    );
  }

  removeFromFavorite(){
    this.interactionService.removeFromFavorite(this.user.userId,this.movieId).subscribe(
      response => {console.log('removed from favorite successfully',response);
      },
      error => {console.error('error removing movie from favorite',error);}
    );
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

  toggleFavorite(){
    if(this.isFavorite){
      this.removeFromFavorite();
    }
    else{
      this.addToFavorite();
    }
    this.isFavorite = !this.isFavorite;
  }

  toggleWatchList(){
    if(this.isWatchList){
      this.removeFromWatchList();
    }
    else{
      this.addToWatchList();
    }
    this.isWatchList = !this.isWatchList;
  }

}
