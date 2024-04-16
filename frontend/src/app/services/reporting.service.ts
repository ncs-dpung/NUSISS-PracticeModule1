import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportingService {

  private apiUrl = 'http://localhost:8080/api/reports';

  constructor(private http: HttpClient) { }

  private getHttpOptions() {
    const token = localStorage.getItem('auth_token'); 
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` 
    });
    return { headers: headers };
  }

  getMonthlyReport(year: number, month: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/monthly-report?year=${year}&month=${month}`, this.getHttpOptions());
  }
}
