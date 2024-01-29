import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-reporting',
  standalone: true,
  imports: [],
  templateUrl: './reporting.component.html',
  styleUrl: './reporting.component.scss'
})
export class ReportingComponent {
  constructor(private router: Router) {} 

  navigate(path: string): void {
    this.router.navigate([path]); 
  }

}
