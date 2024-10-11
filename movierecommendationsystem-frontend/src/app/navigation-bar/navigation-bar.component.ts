import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-navigation-bar',
  standalone: true,
  imports: [CommonModule,FormsModule, RouterLink],
  templateUrl: './navigation-bar.component.html',
  styleUrl: './navigation-bar.component.css'
})
export class NavigationBarComponent implements OnInit {
  searchTerm : string = ''
  login : boolean = false;
  constructor(private authService : AuthService,private router : Router){}
  ngOnInit(): void {
      this.authService.validateToken().subscribe(
        valid => {
          if(valid){
            this.login = true;
          }
        }
      );
  }

  logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.router.navigate(['/login']);
  }

  searchMovies(){
    if (this.searchTerm.trim()) {
      this.router.navigate(['/searchResults'], { queryParams: { query: this.searchTerm } });
    }
  }
  
  watchList(){
    if(this.login){
      this.router.navigate(['/watchlist']);
    }
    else{
      this.router.navigate(['/login']);
    }
  }

  favorite(){
    if(this.login){
      this.router.navigate(['/favorite']);
    }
    else{
      this.router.navigate(['/login']);
    }
  }
}
