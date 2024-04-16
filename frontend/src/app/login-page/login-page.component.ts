import { Component, OnInit  } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service'; 
import { NgClass } from "@angular/common";
import { FormsModule } from "@angular/forms";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [    NgClass,
    FormsModule,
    CommonModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {

  username = ''; 
  password = ''; 
  errorMessage: string | null = null;

  constructor(private router: Router, private userService: UserService) {} 

  navigate(path: string): void {
    this.router.navigate([path]); 
  }

  login(): void {
    this.errorMessage = null;
    this.userService.login(this.username, this.password).subscribe({
      next: (response) => {
 
        this.router.navigate(['/inventory-management']); 
      },
      error: (error) => {
        if (error.status === 401) {
          // Set the error message if unauthorized
          this.errorMessage = 'Invalid username or password.';
        console.error('Login failed', error);
      }else {
        // Handle other potential errors
        this.errorMessage = 'An error occurred during login. Please try again.';
      }
    }
    });
  }

}
