import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Staff } from '../user-access-management/staff.model';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  private apiBaseUrl = 'http://localhost:8080/api/staff';

  constructor(private http: HttpClient) { }

    // CREATE: Add a new staff member
    addStaff(newStaff: Staff): Observable<Staff> {
      return this.http.post<Staff>(this.apiBaseUrl, newStaff);
    }
  
    // READ: Get all staff members
    getAllStaff(): Observable<Staff[]> {
      return this.http.get<Staff[]>(this.apiBaseUrl);
    }
  
    // READ: Get a single staff member by id
    getStaffById(id: number): Observable<Staff> {
      const url = `${this.apiBaseUrl}/${id}`;
      return this.http.get<Staff>(url);
    }
  
    // UPDATE: Update a staff member's details (excluding password)
    updateStaff(updatedStaff: Staff): Observable<Staff> {
      const url = `${this.apiBaseUrl}/${updatedStaff.staff_id}`;
      return this.http.patch<Staff>(url, updatedStaff);
    }
  
    // UPDATE: Change a staff member's password
    changeStaffPassword(id: number, newPassword: string): Observable<any> {
      const url = `${this.apiBaseUrl}/password/${id}`;
      return this.http.put(url, { password: newPassword });
    }
  
    // DELETE: Remove a staff member
    deleteStaff(id: number): Observable<any> {
      const url = `${this.apiBaseUrl}/${id}`;
      return this.http.delete(url);
    }
}
