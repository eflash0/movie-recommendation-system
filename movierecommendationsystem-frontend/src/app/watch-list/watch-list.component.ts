import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NavigationBarComponent } from "../navigation-bar/navigation-bar.component";
import { UserService } from '../service/user.service';
import { AuthService } from '../service/auth.service';
import { InteractionService } from '../service/interaction.service';
import { Router } from '@angular/router';
import { FooterComponent } from "../footer/footer.component";
import { MatDialog } from '@angular/material/dialog';
import { ConfirmDialogComponent } from '../confirm-dialog/confirm-dialog.component';
import { General } from '../model/generalInfo';
import { User } from '../model/user';

@Component({
  selector: 'app-watch-list',
  standalone: true,
  imports: [CommonModule, NavigationBarComponent, FooterComponent],
  templateUrl: './watch-list.component.html',
  styleUrl: './watch-list.component.css'
})
export class WatchListComponent implements OnInit {
  watchList : General[] = [];
  username : string = '';
  user : User | null = null;
  constructor(private userService : UserService,private authService : AuthService,
    private interactionService : InteractionService, private router : Router,
    private dialog : MatDialog
  ){}
  ngOnInit(): void {
    this.authService.extractUsernameFromToken().subscribe(
      response => {this.username = response.username;
        console.log(this.username);
        
        this.userService.findByUsename(this.username).subscribe(
          response => {
            this.user = response;
            if(this.user){
              this.interactionService.watchList(this.user.userId).subscribe(
                response => {this.watchList = response;},
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

  removeFromWatchList(movieId : number){
    const dialogRef = this.dialog.open(ConfirmDialogComponent,{
      data : {message : 'Are you sure you want to remove this movie from your watch list?'}});
    dialogRef.afterClosed().subscribe(
      response => {
        if(response && this.user){
          this.interactionService.removeFromWatchList(this.user.userId,movieId).subscribe(
            response => {this.ngOnInit();},
            error => {console.error('error deleting movie from watch list',error);}
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
