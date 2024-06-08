import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from 'app/interfaces/category.interface';
import { environment } from 'app/util/enviroment';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {
  baseUrl = environment.apiBaseUrl
  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.baseUrl}/category`); 
  }
  postCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(`${this.baseUrl}/category`, category);
  }
  deleteCategory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/category/${id}`);
  }
  putCategory(id: number, category:Category): Observable<Category>{
    return this.http.put<Category>(`${this.baseUrl}/category/${id}`, category)
  }
}