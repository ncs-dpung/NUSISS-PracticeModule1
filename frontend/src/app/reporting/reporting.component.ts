import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {NgClass} from "@angular/common";
import {FormsModule} from "@angular/forms";


@Component({
  selector: 'app-reporting',
  standalone: true,
  imports: [
      NgClass,
      FormsModule],
  templateUrl: './reporting.component.html',
  styleUrl: './reporting.component.scss'
})
export class ReportingComponent {
  constructor(private router: Router) {}

  navigate(path: string): void {
    this.router.navigate([path]);
  }

}
