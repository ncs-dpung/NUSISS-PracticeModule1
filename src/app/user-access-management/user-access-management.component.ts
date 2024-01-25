import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-access-management',
  standalone: true,
  imports: [],
  templateUrl: './user-access-management.component.html',
  styleUrl: './user-access-management.component.scss'
})
export class UserAccessManagementComponent {

  constructor(private router: Router) {} 

  navigate(path: string): void {
    this.router.navigate([path]); 
  }

}
