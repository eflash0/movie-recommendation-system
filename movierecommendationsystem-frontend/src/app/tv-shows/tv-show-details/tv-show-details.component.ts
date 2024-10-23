import { Component, OnInit } from '@angular/core';
import { FooterComponent } from "../../footer/footer.component";
import { NavigationBarComponent } from "../../navigation-bar/navigation-bar.component";
import { CommonModule } from '@angular/common';
import { AuthService } from '../../service/auth.service';
import { TvShowService } from '../../service/tv-show.service';
import { UserService } from '../../service/user.service';
import { InteractionService } from '../../service/interaction.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { TrailerDialogComponent } from '../../trailer-dialog/trailer-dialog.component';
import { FormsModule } from '@angular/forms';
import { TvShow } from '../../model/tvShow';
import { Interaction } from '../../model/interaction';
import { User } from '../../model/user';
import { MediaVideo } from '../../model/mediaVideo';

@Component({
  selector: 'app-tv-show-details',
  standalone: true,
  imports: [FooterComponent, NavigationBarComponent, CommonModule, FormsModule],
  templateUrl: './tv-show-details.component.html',
  styleUrl: './tv-show-details.component.css'
})
export class TvShowDetailsComponent implements OnInit {
  tvShow : TvShow | null = null;
  isWatchList : boolean = false;
  isFavorite : boolean = false;
  username : string = '';
  user : User | null = null;
  tvShowId : number = 0;
  interaction : Interaction | null = null;
  userRating : number = 0;
  trailer : MediaVideo | null = null;
  constructor(private authService : AuthService,private tvShowService : TvShowService,
    private userService : UserService, private interactionService : InteractionService,
    private route : ActivatedRoute, private router : Router,
    private dialog : MatDialog
  ){ }
  ngOnInit(): void {
    this.tvShowId = +this.route.snapshot.paramMap.get('id')!;
    this.getTvShowDetails();
    this.getTrailer();
    this.authService.extractUsernameFromToken().subscribe(
      response => {this.username = response.username;
        this.userService.findByUsename(this.username).subscribe(
          response => {this.user = response;
            if(this.user){
              this.interactionService.findInteraction(this.user.userId,this.tvShowId).subscribe(
                response => {this.interaction = response;
                  if(this.interaction){
                    this.userRating = this.interaction.rating;
                    this.isWatchList = this.interaction.watchList;
                    this.isFavorite = this.interaction.favorite;
                  }       
                },
                error => {console.error('error fetching interaction',error);}
              );
            }
          }
        );
      },
      error => {console.error('error fetching user',error);}
    );
  }

  getTvShowDetails(){
    this.tvShowService.getTvShowDetails(this.tvShowId).subscribe(
      response => {this.tvShow = response;},
      error => {console.error('error fetching tv show details',error);}
    );
  }

  getTrailer(){
    this.tvShowService.getTvShowTrailer(this.tvShowId).subscribe(
      response => {this.trailer = response;},
      error => {console.error('error fetching trailer',error);}
    );
  }

  addToWatchList(){
    if(this.username && this.user){
      this.interactionService.addToWatchList(this.user.userId,this.tvShowId,'tv show').subscribe(
        response => {console.log('added with success',response);
        },
        error => {console.error('error adding tv show to watch list',error);}
      );
    }
    else{
      this.router.navigate(['/login']);
    }
  }

  removeFromWatchList(){
    if(this.username && this.user){
      this.interactionService.removeFromWatchList(this.user.userId,this.tvShowId).subscribe(
        response => {console.log('removed with success',response);
        },
        error => {console.error('error removing tv show from watch list',error);}
      );
    }
    else{
      this.router.navigate(['/login']);
    }
  }

  addToFavorite(){
    if(this.username && this.user){
      this.interactionService.addToFavorite(this.user.userId,this.tvShowId,'tv show').subscribe(
        response => {console.log('added with success',response);
        },
        error => {console.error('error adding tv show to favorite',error);}
      );
    }
    else{
      this.router.navigate(['/login']);
    }
  }

  removeFromFavorite(){
    if(this.username && this.user){
      this.interactionService.removeFromFavorite(this.user.userId,this.tvShowId).subscribe(
        response => {console.log('removed with success',response);
        },
        error => {console.error('error removing tv show from favorite',error);}
      );
    }
    else{
      this.router.navigate(['/login']);
    }
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

  toggleTrailer(){
    if(this.trailer){
      this.dialog.open(TrailerDialogComponent, {data : {trailerKey : this.trailer.key}});
    }
  }

  rateTvShow(){
    if(this.username && this.user){
      this.interactionService.rate(this.user.userId,this.tvShowId,this.userRating,'tv show').subscribe(
        response => {this.ngOnInit()},
        error => {console.error('error rating the movie',error);}
      );
    }
  }

  seasonDetails(seasonNumber : number){
    this.router.navigate(['/tv-shows',this.tvShowId,'seasons',seasonNumber]);
  }
}
