import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../order-management/order.model';
import { Order_Items } from '../order-management/order_items.model';
import { Order_Status } from '../order-management/order_status.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = 'http://your-api-url/api/orders';

  constructor(private http: HttpClient) { }

  
  getOrderById(orderId: number): Observable<Order> {
    return this.http.get<Order>(`${this.apiUrl}/${orderId}`);
  }

  createOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(this.apiUrl, order);
  }

  updateOrder(orderId: number, order: Order): Observable<Order> {
    return this.http.put<Order>(`${this.apiUrl}/${orderId}`, order);
  }

  deleteOrder(orderId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${orderId}`);
  }

  updateOrderStatus(orderId: number, status: any): Observable<Order> {
    return this.http.put<Order>(`${this.apiUrl}/${orderId}/status`, status);
  }

  getAllOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/all`);
  }

  getPendingProcessedOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/pending processed`);
  }

}
