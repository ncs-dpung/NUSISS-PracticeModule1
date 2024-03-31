import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportingService {

  private apiUrl = 'http://localhost:8080/api/reports';

  constructor(private http: HttpClient) { }

  getMonthlyReport(year: number, month: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/monthly-report?year=${year}&month=${month}`);
  }
}
