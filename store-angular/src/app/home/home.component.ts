import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ProductsComponent } from 'app/products/products.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ProductsComponent, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  title = 'store-angular';
  showProducts = false; 
  toggleProducts() {
    this.showProducts = !this.showProducts;
  }

}
