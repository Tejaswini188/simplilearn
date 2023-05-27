import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from './product';
import { environment } from './../environments/environment.prod';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private baseUrl = 'http://localhost:8080/api/product';
  constructor(private http: HttpClient) {}

  getProductList(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/all`);
  }
  addProduct(
    name: String,
    url_: String,
    msrp_: String
  ): Observable<ArrayBuffer> {
    return this.http.post<ArrayBuffer>(`${this.baseUrl}/add`, {
      product_name: name,
      url: url_,
      msrp: msrp_,
    });
  }
  deleteProduct(product_id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${product_id}`);
  }
  getProduct(product_id: number): Observable<ArrayBuffer> {
    return this.http.get<ArrayBuffer>(`${this.baseUrl}/${product_id}`);
  }
  searchProduct(name: String): Observable<Product> {
    return this.http.get<Product>(`${this.baseUrl}/search/?name=${name}`);
  }
}
