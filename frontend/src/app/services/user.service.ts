import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../customer-management/customer.model';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/auth/login';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<{ jwt: string }> {
    return this.http.post<{ jwt: string }>(this.apiUrl, { username, password }).pipe(
      tap(response => {
 
        localStorage.setItem('auth_token', response.jwt);
      })
    );
  }


}
