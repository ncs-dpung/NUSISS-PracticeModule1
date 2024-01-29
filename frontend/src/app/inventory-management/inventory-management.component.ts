import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inventory-management',
  standalone: true,
  imports: [],
  templateUrl: './inventory-management.component.html',
  styleUrl: './inventory-management.component.scss'
})
export class InventoryManagementComponent {

  constructor(private router: Router) {} 

  navigate(path: string): void {
    this.router.navigate([path]); 
  }

}
