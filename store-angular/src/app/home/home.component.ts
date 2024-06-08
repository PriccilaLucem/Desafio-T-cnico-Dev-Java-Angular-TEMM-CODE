import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CategoriesComponent } from 'app/categories/categories.component';
import { ProductsComponent } from 'app/products/products.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ProductsComponent,
    CommonModule,
    CategoriesComponent,
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  title = 'store-angular';
  showProducts = false; 
  showCategories = false; 
  
  toggleProducts() {
    this.showProducts = !this.showProducts;
    this.showCategories = false;
  }
  toggleCategories(){
    this.showCategories = !this.showCategories;
    this.showProducts = false;
  }

}
