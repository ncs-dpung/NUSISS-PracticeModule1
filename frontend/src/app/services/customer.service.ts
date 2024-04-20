import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../customer-management/customer.model';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private apiUrl = '/api/customer';

  constructor(private http: HttpClient) { }

  private getHttpOptions() {
    const token = localStorage.getItem('auth_token'); 
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` 
    });
    return { headers: headers };
  }


  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.apiUrl,this.getHttpOptions());
  }

  getCustomerById(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${id}`,this.getHttpOptions());
  }

  createCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.apiUrl, customer,this.getHttpOptions());
  }

  updateCustomer(customer: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${this.apiUrl}/${customer.id}`, customer,this.getHttpOptions());
  }

  deleteCustomer(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`,this.getHttpOptions());
  }
}
