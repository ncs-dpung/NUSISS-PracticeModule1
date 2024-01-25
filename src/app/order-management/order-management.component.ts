import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-management',
  standalone: true,
  imports: [],
  templateUrl: './order-management.component.html',
  styleUrl: './order-management.component.scss'
})
export class OrderManagementComponent {

  constructor(private router: Router) {} 

  navigate(path: string): void {
    this.router.navigate([path]); 
  }

}
