import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../order-management/order.model';
import { Order_Items } from '../order-management/order_items.model';
import { OrderStatus } from '../order-management/order_status.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = 'http://localhost:8080/api/orders';

  constructor(private http: HttpClient) { }

  private getHttpOptions() {
    const token = localStorage.getItem('auth_token'); 
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}` 
    });
    return { headers: headers };
  }
  
  getOrderById(orderId: number): Observable<Order> {
    return this.http.get<Order>(`${this.apiUrl}/${orderId}`,this.getHttpOptions());
  }

  createOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(this.apiUrl, order,this.getHttpOptions());
  }

  updateOrder(orderId: number, order: Order): Observable<Order> {
    return this.http.put<Order>(`${this.apiUrl}/${orderId}`, order,this.getHttpOptions());
  }


  deleteOrder(orderId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${orderId}`,this.getHttpOptions());
  }

  updateOrderStatus(orderId: number, status: any): Observable<Order> {
    return this.http.put<Order>(`${this.apiUrl}/${orderId}/status`, status,this.getHttpOptions());
  }

  getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/all`,this.getHttpOptions());
  }

  getPendingProcessedOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/pending processed`,this.getHttpOptions());
  }

}
