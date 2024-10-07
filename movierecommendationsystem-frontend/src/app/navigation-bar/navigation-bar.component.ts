import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation-bar',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './navigation-bar.component.html',
  styleUrl: './navigation-bar.component.css'
})
export class NavigationBarComponent implements OnInit {
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
}
