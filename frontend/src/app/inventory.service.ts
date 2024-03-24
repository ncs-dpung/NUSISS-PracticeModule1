import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
private apiUrl = 'http://localhost:8080/inventory'; 

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }
  /*
 // Create (POST)
 createItem(ItemData: any): Observable<any> {
  return this.http.post<any>(this.apiUrl, ItemData, this.httpOptions);
}

// Read (GET)
getAllItem(): Observable<any[]> {
  return this.http.get<any[]>(this.apiUrl);
}

// Read single item (GET)
getItemById(id: string): Observable<any> {
  const url = `${this.apiUrl}/${id}`;
  return this.http.get<any>(url);
}

// Update (PUT)
updateItem(id: string, supplierData: any): Observable<any> {
  const url = `${this.apiUrl}/${id}`;
  return this.http.put<any>(url, supplierData, this.httpOptions);
}

// Delete (DELETE)
deleteItem(id: string): Observable<any> {
  const url = `${this.apiUrl}/${id}`;
  return this.http.delete<any>(url, this.httpOptions);
}*/

}
