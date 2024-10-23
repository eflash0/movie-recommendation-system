import { Component, OnInit } from '@angular/core';
import { FooterComponent } from "../footer/footer.component";
import { NavigationBarComponent } from "../navigation-bar/navigation-bar.component";
import { CommonModule } from '@angular/common';
import { UserService } from '../service/user.service';
import { AuthService } from '../service/auth.service';
import { InteractionService } from '../service/interaction.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { General } from '../model/generalInfo';
import { User } from '../model/user';

@Component({
  selector: 'app-favorite-movies',
  standalone: true,
  imports: [FooterComponent, NavigationBarComponent, CommonModule],
  templateUrl: './favorite-movies.component.html',
  styleUrl: './favorite-movies.component.css'
})
export class FavoriteMoviesComponent implements OnInit {
  favorite : General[] = [];
  username : string = '';
  user : User | null = null;
  constructor(private userService : UserService,private authService : AuthService,
    private interactionService : InteractionService, private router : Router,
  private dialog : MatDialog){}
  ngOnInit(): void {
    this.authService.extractUsernameFromToken().subscribe(
      response => {this.username = response.username;
        this.userService.findByUsename(this.username).subscribe(
          response => {
            this.user = response;
            if(this.user){
              this.interactionService.favorite(this.user.userId).subscribe(
                response => {this.favorite = response;},
                error => {console.error('error fetching watch list',error);}
              );
            }
          },
          error => {console.error('error fetching user',error);}
        );
      },
      error => {console.error('error extracting username',error);}
    );
  }

  removeFromFavorite(movieId : number){
    const dialogRef = this.dialog.open(ConfirmDialogComponent,{
      data : {message : 'Are you sure you want to remove this movie from your favorite?'}});
    dialogRef.afterClosed().subscribe(
      response => {
        if(response && this.user){
          this.interactionService.removeFromFavorite(this.user.userId,movieId).subscribe(
            response => {this.ngOnInit();},
            error => {console.error('error deleting movie from favorite',error);}
          );
        }
      }
    );
  }

  mediaDetails(media : any){
    if(media.title){
      this.router.navigate(['/movies',media.id]);
    }
    else{
      this.router.navigate(['/tv-shows',media.id]);
    }
  }
}
