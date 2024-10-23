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
    this.login = false;
    this.router.navigate(['/movies']);
  }

  searchMovies(){
    if (this.searchTerm.trim()) {
      this.router.navigate(['/searchResults'], { queryParams: { query: this.searchTerm } });
    }
  }
  
  watchList(){
    this.router.navigate(['/watchlist']);
  }

  favorite(){
    this.router.navigate(['/favorite']);
  }

  recommendations(){
    this.router.navigate(['/recommendations']);
  }
}
