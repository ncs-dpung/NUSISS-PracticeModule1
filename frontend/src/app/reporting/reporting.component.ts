import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NgClass} from "@angular/common";
import {FormsModule} from "@angular/forms";
import { Chart, registerables } from 'chart.js';

@Component({
  selector: 'app-reporting',
  standalone: true,
  imports: [
      NgClass,
      FormsModule],
  templateUrl: './reporting.component.html',
  styleUrl: './reporting.component.scss'
})
export class ReportingComponent implements OnInit {

//dropdown for month
 selectedMonth: string = new Date().getMonth().toString().padStart(2, '0');
 selectedYear: string = new Date().getFullYear().toString();

   updateDateHeader(): void {
     // Logic to update something on date change if needed

   }

  ngOnInit(): void {
this.initBarChart();
 this.initPieChart(); // Initialize the pie chart as well
  }


  constructor(private router: Router) {
      Chart.register(...registerables);
      }

  navigate(path: string): void {
    this.router.navigate([path]);
  }

  initBarChart(): void {
      const ctx = (document.getElementById('barChart') as HTMLCanvasElement)?.getContext('2d');
      if (ctx) {
        const barChart = new Chart(ctx, {
          type: 'bar',
          data: {
            labels: ['Category 1', 'Category 2', 'Category 3', 'Category 4', 'Category 5', 'Category 6'],
            datasets: [{
              label: 'Number of Products',
              data: [65, 59, 80, 81, 56, 55],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
              ],
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              y: {
                beginAtZero: true
              }
            }
          }
        });
      }
    }

      initPieChart(): void {
        const ctx = (document.getElementById('pieChart') as HTMLCanvasElement)?.getContext('2d');
        if (ctx) {
          const pieChart = new Chart(ctx, {
            type: 'pie',
            data: {
              labels: ['Product A', 'Product B', 'Product C', 'Product D'],
              datasets: [{
                label: 'Revenue Distribution',
                data: [300, 50, 100, 150],
                backgroundColor: [
                  'rgba(255, 99, 132, 0.6)',
                  'rgba(54, 162, 235, 0.6)',
                  'rgba(255, 206, 86, 0.6)',
                  'rgba(75, 192, 192, 0.6)'
                ],
                hoverOffset: 4
              }]
            },
            options: {
              responsive: true,
              aspectRatio: 1,
              plugins: {
                legend: {
                  position: 'top',
                },
                tooltip: {
                  enabled: true
                }
              }
            }
          });
        }
      }



}
