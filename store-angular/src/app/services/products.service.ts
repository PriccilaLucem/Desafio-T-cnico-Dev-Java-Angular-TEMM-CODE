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
  postProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.baseUrl}/product`, product);
  }
  deleteProduct(id:number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/product/${id}`);
  }
  putProduct(id:number, product:Product): Observable<Product>{
    return this.http.put<Product>(`${this.baseUrl}/product/${id}`, product)
  }
  // filterByProductName(name: string){
  //   return this.http.get<Product[]>(`${this.baseUrl}/product`); 
  // }
}
