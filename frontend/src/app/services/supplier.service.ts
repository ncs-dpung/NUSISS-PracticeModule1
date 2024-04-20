import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Supplier } from '../supplier-management/supplier.model';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  private apiUrl = '/api'; // Your API base URL

  constructor(private http: HttpClient) { }
  
  private getHttpOptions() {
    const token = localStorage.getItem('auth_token'); 
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` 
    });
    return { headers: headers };
  }
  getSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.apiUrl}/supplier`, this.getHttpOptions());
  }

  getSupplierById(id: number): Observable<Supplier> {
    return this.http.get<Supplier>(`${this.apiUrl}/supplier/${id}`, this.getHttpOptions());
  }

  createSupplier(supplier: Supplier): Observable<Supplier> {
    return this.http.post<Supplier>(`${this.apiUrl}/supplier`, supplier, this.getHttpOptions());
  }

  updateSupplier(supplier: Supplier): Observable<Supplier> {
    return this.http.put<Supplier>(`${this.apiUrl}/supplier/${supplier.id}`, supplier, this.getHttpOptions());
  }

  deleteSupplier(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/supplier/${id}`, this.getHttpOptions());
  }
}
