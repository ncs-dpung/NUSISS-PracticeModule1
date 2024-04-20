import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inventory } from '../inventory-management/inventory.model';
import { Product } from '../inventory-management/product.model';
@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private apiUrl = '/api/products';

  constructor(private http: HttpClient) { }

   
    private getHttpOptions() {
      const token = localStorage.getItem('auth_token'); 
      let headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}` 
      });
      return { headers: headers };
    }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl, this.getHttpOptions());
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`, this.getHttpOptions());
  }

  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, product, this.getHttpOptions());
  }

  updateProduct(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiUrl}/${id}`, product, this.getHttpOptions());
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`, this.getHttpOptions());
  }


  // Method to get products that need reordering
  getProductsNeedingReorder(): Observable<Product[]> {
    const url = `${this.apiUrl}/inventory/products/needing-reorder`;
    return this.http.get<Product[]>(url);
  }

  // Method to reorder a product
  reorderProduct(id: number): Observable<any> {
    const url = `${this.apiUrl}/inventory/reorder/product/${id}`;

    // data detail
    return this.http.put(url, {});
  }

}
