import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Staff, User } from '../user-access-management/staff.model';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  private apiBaseUrl = '/api/staff';

  private apiBaseUrlUser = 'http://localhost:8080/users';


  constructor(private http: HttpClient) { }

  private getHttpOptions() {
    const token = localStorage.getItem('auth_token'); 
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` 
    });
    return { headers: headers };
  }



    addStaff(newStaff: Staff): Observable<Staff> {
      return this.http.post<Staff>(this.apiBaseUrl, newStaff, this.getHttpOptions());
    }
  

    getAllStaff(): Observable<Staff[]> {
      return this.http.get<Staff[]>(this.apiBaseUrl, this.getHttpOptions());
    }

    getAllUser(): Observable<User[]> {
      return this.http.get<User[]>(this.apiBaseUrlUser, this.getHttpOptions());
    }
  

    getStaffById(id: number): Observable<Staff> {
      const url = `${this.apiBaseUrl}/${id}`;
      return this.http.get<Staff>(url, this.getHttpOptions());
    }
  

    updateStaff(updatedStaff: Staff): Observable<Staff> {
      const url = `${this.apiBaseUrl}/${updatedStaff.id}`;
      return this.http.patch<Staff>(url, updatedStaff, this.getHttpOptions());
    }

    changeStaffPassword(id: number, newPassword: string): Observable<any> {
      const url = `${this.apiBaseUrl}/password/${id}`;
      return this.http.put(url, { password: newPassword }, this.getHttpOptions());
    }
  
    deleteStaff(id: number): Observable<any> {
      const url = `${this.apiBaseUrl}/${id}`;
      return this.http.delete(url, this.getHttpOptions());
    }
}
