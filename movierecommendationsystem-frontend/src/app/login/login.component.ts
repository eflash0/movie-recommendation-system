import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  loginData = {
    username : '',
    password : ''
  }
  loginError = '';
  constructor(private authService : AuthService, private router : Router) { }
  
  ngOnInit(): void {
      
  }

  onLogin(){
    this.authService.login(this.loginData).subscribe(
      response => {
        this.router.navigate(['/movies']);
      },
      error => {
        this.loginError = 'Credentials are invalid';
        console.error('credentials are invalid',error);
      }
    );
  }
  continueWithGoogle(){
    window.location.href = 'http://localhost:8082/oauth2/authorization/google';
  }

  oauth2Login(code: string) {
    this.authService.oauth2Login(code).subscribe(
      response => {
        console.log('OAuth2 Login successful:', response);
        this.router.navigate(['/movies']);
      },
      error => {
        console.error('OAuth2 Login failed:', error);
      }
    );
  }
}
