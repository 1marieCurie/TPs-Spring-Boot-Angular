import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly TOKEN_KEY = 'jwt-token';

  constructor(private http: HttpClient) {}

  login(credentials: any): Observable<any> {
    return this.http.post("http://localhost:8080/api/auth/login", credentials);
  }

  register(data: any): Observable<any> {
    return this.http.post("http://localhost:8080/api/auth/register", data);
  }

  saveToken(token: string) {
    localStorage.setItem(this.TOKEN_KEY, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  isLogged(): boolean {
    return this.getToken() !== null;
  }

  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
  }
}
