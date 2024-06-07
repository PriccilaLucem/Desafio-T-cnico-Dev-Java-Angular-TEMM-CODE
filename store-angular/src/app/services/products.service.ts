import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from 'app/interfaces/product.interface';
import { environment } from 'app/util/enviroment';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  baseUrl = environment.apiBaseUrl
  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/product`); 
  }
}
