import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { ReportingService } from './reporting.service';

fdescribe('ReportingService', () => {
  let service: ReportingService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ReportingService],
    });
    service = TestBed.inject(ReportingService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve monthly report', () => {
    const year = 2024;
    const month = 3;

    // Set up the expected structure using jasmine matchers
    const expectedStructure = {
      totalSales: jasmine.objectContaining({
        year: jasmine.any(Number),
        month: jasmine.any(Number),
        totalOrders: jasmine.any(Number),
        totalRevenue: jasmine.any(Number),
        mostSoldProduct: jasmine.any(String),
      }),
      topSellingProducts: jasmine.any(Array),
      revenueByCategory: jasmine.any(Array),
    };

    // Subscribe to the service method
    service.getMonthlyReport(year, month).subscribe((report) => {
      expect(report).toEqual(expectedStructure);
    });

    // Set up the HttpTestingController to expect a GET request
    const req = httpMock.expectOne(
      `/api/reports/monthly-report?year=${year}&month=${month}`
    );
    expect(req.request.method).toBe('GET');

    // Respond with a mock object that matches the structure
    req.flush({
      totalSales: {
        year: year,
        month: month,
        totalOrders: 5,
        totalRevenue: 10000,
        mostSoldProduct: 'Sample',
      },
      topSellingProducts: [{ productName: 'Sample A', totalQuantity: 50 }],
      revenueByCategory: [
        { categoryName: 'Category A', revenue: 5000 },
        { categoryName: 'Category B', revenue: 3000 },
      ],
    });
  });
});
