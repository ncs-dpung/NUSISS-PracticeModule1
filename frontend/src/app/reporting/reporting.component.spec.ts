import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReportingComponent } from './reporting.component';
import { ReportingService } from '../services/reporting.service';
import { RouterTestingModule } from '@angular/router/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

fdescribe('ReportingComponent', () => {
  let component: ReportingComponent;
  let fixture: ComponentFixture<ReportingComponent>;
  let mockReportService: jasmine.SpyObj<ReportingService>;

  const mockMonthlyReport = {
    totalSales: {
      year: 2024,
      month: 3,
      totalOrders: 10,
      totalRevenue: 1000,
      mostSoldProduct: 'Mock Product',
    },
    topSellingProducts: [
      { productName: 'Mock Product 1', totalQuantity: 100 },
      { productName: 'Mock Product 2', totalQuantity: 90 },
      { productName: 'Mock Product 3', totalQuantity: 80 },
      { productName: 'Mock Product 4', totalQuantity: 70 },
      { productName: 'Mock Product 5', totalQuantity: 60 },
      { productName: 'Mock Product 6', totalQuantity: 50 },
    ],
    revenueByCategory: [
      { categoryName: 'Mock Category 1', revenue: 500 },
      { categoryName: 'Mock Category 2', revenue: 500 },
    ],
  };

  beforeEach(async () => {
    mockReportService = jasmine.createSpyObj('ReportingService', [
      'getMonthlyReport',
    ]);

    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        FormsModule,
        CommonModule,
        ReportingComponent,
      ],
      providers: [{ provide: ReportingService, useValue: mockReportService }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReportingComponent);
    component = fixture.componentInstance;
    mockReportService.getMonthlyReport.and.returnValue(of(mockMonthlyReport));
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should update reportData correctly with mock data from service on init', () => {
    const year = new Date().getFullYear();
    const month = new Date().getMonth() + 1;
    expect(mockReportService.getMonthlyReport).toHaveBeenCalledWith(
      year,
      month
    );

    expect(component.reportData).toEqual(mockMonthlyReport);
  });
});
