import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private baseUrl = 'http://your-api-base-url.com/api'; // Replace with your API's base URL

  constructor(private http: HttpClient) { }

  fetchData(): Observable<any> {
    return this.http.get(`${this.baseUrl}/your-endpoint`); // Replace '/your-endpoint' with actual endpoint
  }

}
