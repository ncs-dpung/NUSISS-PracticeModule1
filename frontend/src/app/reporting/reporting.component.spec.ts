// import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { RouterTestingModule } from '@angular/router/testing';
// import { FormsModule } from '@angular/forms';
// import { CommonModule } from '@angular/common';
// import { Chart, registerables } from 'chart.js';
// import { ReportingComponent } from './reporting.component';
// import { ReportingService } from './reporting.service';
// import { Observable } from 'rxjs';
// import { MonthNamePipe } from './month-name-pipe';

// describe('ReportingComponent', () => {
//   let component: ReportingComponent;
//   let fixture: ComponentFixture<ReportingComponent>;
//   let reportingService: ReportingService;

//   beforeEach(async () => {
//     await TestBed.configureTestingModule({
//       imports: [RouterTestingModule, FormsModule, CommonModule],
//       declarations: [ReportingComponent],
//       providers: [ReportingService],
//     }).compileComponents();
//   });

//   beforeEach(() => {
//     fixture = TestBed.createComponent(ReportingComponent);
//     component = fixture.componentInstance;
//     reportingService = TestBed.inject(ReportingService);
//     fixture.detectChanges();
//   });

//   it('should create', () => {
//     expect(component).toBeTruthy();
//   });

//   it('should fetch and display report data', () => {
//     const mockReportData = {
//       totalSales: {
//         year: 2022,
//         month: 10,
//         totalOrders: 100,
//         totalRevenue: 5000,
//         mostSoldProduct: 'Product A',
//       },
//       topSellingProducts: [
//         { productName: 'Product A', totalQuantity: 50 }
//       ],
//       revenueByCategory: [
//         { categoryName: 'Category A', revenue: 2000 },
//         { categoryName: 'Category B', revenue: 3000 },
//       ],
//     };

//     spyOn(reportingService, 'getMonthlyReport').and.returnValue(Promise.resolve(mockReportData) as Observable<any>);

//     component.fetchAndDisplayReport();

//     expect(reportingService.getReportData).toHaveBeenCalled();
//     expect(component.reportData).toEqual(mockReportData);
//   });

//   it('should navigate to the specified path', () => {
//     const routerSpy = spyOn(component.router, 'navigateByUrl');

//     component.navigate('/dashboard');

//     expect(routerSpy).toHaveBeenCalledWith('/dashboard');
//   });

//   it('should initialize bar chart', () => {
//     const topSellingProducts = [
//       { productName: 'Product A', totalQuantity: 50 },
//       { productName: 'Product B', totalQuantity: 30 },
//     ];

//     component.initBarChart(topSellingProducts);

//     expect(component.barChart).toBeDefined();
//     // Add more assertions for the bar chart initialization if needed
//   });

//   it('should initialize pie chart', () => {
//     const revenueByCategory = [
//       { categoryName: 'Category A', revenue: 2000 },
//       { categoryName: 'Category B', revenue: 3000 },
//     ];

//     component.initPieChart(revenueByCategory);

//     expect(component.pieChart).toBeDefined();
//     // Add more assertions for the pie chart initialization if needed
//   });
// });
