import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ReportingService } from './reporting.service';

describe('ReportingService', () => {
  let service: ReportingService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ReportingService]
    });
    service = TestBed.inject(ReportingService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should retrieve monthly report', () => {
    const year = 2022;
    const month = 1;
    const expectedReport = { /* your expected report object */ };

    service.getMonthlyReport(year, month).subscribe(report => {
      expect(report).toEqual(expectedReport);
    });

    const req = httpMock.expectOne(`http://localhost:8080/api/reports?year=${year}&month=${month}`);
    expect(req.request.method).toBe('GET');
    req.flush(expectedReport);
  });
});
