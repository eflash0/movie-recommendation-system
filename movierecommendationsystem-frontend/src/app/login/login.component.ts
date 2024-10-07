import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
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
  loginResponse : any;
  constructor(private authService : AuthService, private router : Router,
    private route : ActivatedRoute) { }
  
  ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        const response = params['response'];
        if (response) {
          this.loginResponse = JSON.parse(decodeURIComponent(response));
          localStorage.setItem('token',this.loginResponse.token);
          localStorage.setItem('role',this.loginResponse.role);
          console.log('User object:', this.loginResponse);
        }
      });
      console.log(this.loginResponse);
      
      this.authService.validateToken().subscribe(
        valid => {
          if(valid){
            this.router.navigate(['/movies']);
          }
        },
        error => {this.router.navigate(['/login']);}
      );
  }

  onLogin(){
    this.authService.login(this.loginData).subscribe(
      response => {
        this.router.navigate(['/movies']);
        localStorage.setItem('token',response.token);
        localStorage.setItem('role',response.role);
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

  // oauth2Login(code: string) {
  //   this.authService.oauth2Login(code).subscribe(
  //     response => {
  //       console.log('OAuth2 Login successful:', response);
  //       this.router.navigate(['/movies']);
  //     },
  //     error => {
  //       console.error('OAuth2 Login failed:', error);
  //     }
  //   );
  // }
}
