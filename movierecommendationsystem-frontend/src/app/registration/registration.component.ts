import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.css'
})
export class RegistrationComponent {
  registrationRequest = {
    username : '',
    email : '',
    password : '',
    confirmPassword : ''
  };
  signupError = '';
  constructor(private userService : UserService, private router : Router){ }

  onSignup() {
    if (this.registrationRequest.password !== this.registrationRequest.confirmPassword) {
      this.signupError = 'Password must be equal to the confirm password';
      return;
    }
  
    this.userService.findByUsename(this.registrationRequest.username).subscribe(
      response => {
        this.signupError = 'Username already exists';
      },
      error => {
        this.userService.findByEmail(this.registrationRequest.email).subscribe(
          response => {
            this.signupError = 'Email already exists';
          },
          error => {
            this.userService.register(this.registrationRequest).subscribe(
              response => {
                this.router.navigate(['/login']);
              },
              error => {
                this.signupError = 'An error occurred during the registration';
                console.error('An error occurred during the registration', error);
              }
            );
          }
        );
      }
    );
  }
  
}
