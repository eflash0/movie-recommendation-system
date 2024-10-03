import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
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
export class LoginComponent {
  loginData = {
    username : '',
    password : ''
  }
  loginError = '';
  constructor(private authService : AuthService, private router : Router) { }
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
}
