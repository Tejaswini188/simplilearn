import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from './admin';

@Injectable({
  providedIn: 'root',
})
export class AdminsService {
  private baseUrl = 'http://localhost:8080/api/admin';
  constructor(private http: HttpClient) {}

  login(admin_id: number, password: String): Observable<any> {
    return this.http.get<any>(
      `${this.baseUrl}/signin?admin_id=${admin_id}&password=${password}`
    );
  }
  changePassword(id: number, pass: String): Observable<ArrayBuffer> {
    return this.http.patch<ArrayBuffer>(
      `${this.baseUrl}/${id}/update/password`,
      {
        admin_id: id,
        password: pass,
      }
    );
  }
}
