import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductsService } from '../services/products.service';
import { Product } from 'app/interfaces/product.interface';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-products',
  standalone:true,
  imports:[CommonModule],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products$!: Observable<Product[]>;

  constructor(private productService: ProductsService) { }
  showProducts: boolean = true;
  ngOnInit(): void {
    this.products$ = this.productService.getProducts();
  }
}
