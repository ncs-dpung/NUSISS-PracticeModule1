import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgClass } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Chart, registerables } from 'chart.js';
import { ReportingService } from './reporting.service';
import { MonthlyReport } from './monthly-report-model';
import { CommonModule } from '@angular/common';

interface TopSellingProduct {
  productName: string;
  totalQuantity: number;
}

interface RevenueByCategory {
  categoryName: string;
  revenue: number;
}

@Component({
  selector: 'app-reporting',
  standalone: true,
  imports: [NgClass, FormsModule, CommonModule],
  templateUrl: './reporting.component.html',
  styleUrl: './reporting.component.scss',
})
export class ReportingComponent implements OnInit {
  selectedMonth: string = (new Date().getMonth() + 1)
    .toString()
    .padStart(2, '0');
  selectedYear: string = new Date().getFullYear().toString();

  barChart: any;
  pieChart: any;
  reportData: MonthlyReport = {
    totalSales: {
      year: 0,
      month: 0,
      totalOrders: 0,
      totalRevenue: 0,
      mostSoldProduct: '',
    },
    topSellingProducts: [],
    revenueByCategory: [],
  };
  

  updateDateHeader(): void {
    // Logic to update something on date change if needed
  }

  constructor(private router: Router, private reportService: ReportingService) {
    Chart.register(...registerables);
  }

  ngOnInit(): void {
    this.fetchAndDisplayReport();
  }

  fetchAndDisplayReport(): void {
    const year = parseInt(this.selectedYear, 10);
    const month = parseInt(this.selectedMonth, 10);
    this.reportService.getMonthlyReport(year, month).subscribe((data) => {
      this.reportData = data;
      this.initBarChart(data.topSellingProducts);
      this.initPieChart(data.revenueByCategory);
    });
  }

  getMonthName(monthNumber: number): string {
    if (monthNumber === 0) {
      return 'Nil';
    }
    // Note: Month is 0-indexed in JavaScript Date, so subtract 1.
    const date = new Date();
    date.setMonth(monthNumber + 1);
  
    // Use Angular's DatePipe programmatically or return a formatted string directly.
    // Here's how you might format it directly for simplicity:
    return new Intl.DateTimeFormat('en-US', { month: 'long' }).format(date);
  }

  navigate(path: string): void {
    this.router.navigate([path]);
  }

  initBarChart(topSellingProducts: TopSellingProduct[]): void {
    const ctx = (
      document.getElementById('barChart') as HTMLCanvasElement
    )?.getContext('2d');
    if (ctx) {
      const labels = topSellingProducts.map((product) => product.productName);
      const data = topSellingProducts.map((product) => product.totalQuantity);
      // Clear previous chart instance if exists
      if (this.barChart) {
        this.barChart.destroy();
      }
      this.barChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [
            {
              label: 'Total Quantity Sold per Product',
              data: data,
              backgroundColor: 'rgba(54, 162, 235, 0.2)',
              borderColor: 'rgba(54, 162, 235, 1)',
              borderWidth: 1,
            },
          ],
        },
        options: {
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      });
    }
  }

  initPieChart(revenueByCategory: RevenueByCategory[]): void {
    const ctx = (
      document.getElementById('pieChart') as HTMLCanvasElement
    )?.getContext('2d');
    if (ctx) {
      const labels = revenueByCategory.map((category) => category.categoryName);
      const data = revenueByCategory.map((category) => category.revenue);
      // Clear previous chart instance if exists
      if (this.pieChart) {
        this.pieChart.destroy();
      }
      this.pieChart = new Chart(ctx, {
        type: 'pie',
        data: {
          labels: labels,
          datasets: [
            {
              label: 'Revenue by Category',
              data: data,
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
              ],
              borderColor: [
                'rgba(255,99,132,1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
              ],
              borderWidth: 1,
            },
          ],
        },
        options: {
          responsive: true,
          plugins: {
            legend: {
              position: 'top',
            },
            title: {
              display: true,
              text: 'Revenue by Category',
            },
          },
        },
      });
    }
  }
}
