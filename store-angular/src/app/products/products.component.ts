import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductsService } from '../services/products.service';
import { Product } from 'app/interfaces/product.interface';
import { CommonModule } from '@angular/common';
import { ProductCreateComponent } from 'app/product-create/product-create.component';
import { ProductCreateService } from 'app/services/product-create.service';

@Component({
  selector: 'app-products',
  standalone:true,
  imports:[CommonModule, ProductCreateComponent],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products$!: Observable<Product[]>;
  
  constructor(private productService: ProductsService, private _dialog: ProductCreateService) { }
  showProducts: boolean = true;
  ngOnInit(): void {
    this.products$ = this.productService.getProducts();
  }
  openProductCreate(){
    this._dialog.open({})
  }
}
