import { Component, OnInit } from '@angular/core';
import { FooterComponent } from "../footer/footer.component";
import { NavigationBarComponent } from "../navigation-bar/navigation-bar.component";
import { CommonModule } from '@angular/common';
import { User } from '../model/user';
import { AuthService } from '../service/auth.service';
import { UserService } from '../service/user.service';
import { RecommendationService } from '../service/recommendation.service';
import { Router } from '@angular/router';
import { General } from '../model/generalInfo';

@Component({
  selector: 'app-recommendations',
  standalone: true,
  imports: [FooterComponent, NavigationBarComponent, CommonModule],
  templateUrl: './recommendations.component.html',
  styleUrl: './recommendations.component.css'
})
export class RecommendationsComponent implements OnInit {
  user : User | null = null;
  username : string = '';
  medias : General[] = [];
  loading: boolean = false;
  constructor(private authService : AuthService, private userService : UserService,
    private recommendationService : RecommendationService, private router : Router
  ){}
  ngOnInit(): void {
    this.loading = true;
    this.authService.extractUsernameFromToken().subscribe(
      response => {this.username = response.username;
        this.userService.findByUsename(this.username).subscribe(
          response => {this.user = response;
            if(this.user){
              this.recommendationService.train().subscribe(
                response => {
                  if(this.user){
                    this.recommendationService.getRecommendations(this.user.userId).subscribe(
                      response => {this.medias = response;
                        this.loading = false;
                      },
                      error => {console.error('error fetching recommendations',error);}
                    );
                  }   
                },
                error => {console.error(error);}
              );
            }
          },
          error => {console.error('error fetching user',error);}
        );
      },
      error => {console.error('error extracting username',error);}
    );
  } 

  mediaDetails(media:any){
    if(media.title){
      this.router.navigate(['/movies',media.id]);
    }
    else{
      this.router.navigate(['/tv-shows',media.id])
    }
  }
}
