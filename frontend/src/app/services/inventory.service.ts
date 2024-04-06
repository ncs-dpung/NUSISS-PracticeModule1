import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inventory } from '../inventory-management/inventory.model';
import { Product } from '../inventory-management/product.model';
@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private apiUrl = 'http://localhost:8080/products';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }

  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.apiUrl, product);
  }

  updateProduct(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiUrl}/${id}`, product);
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
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
