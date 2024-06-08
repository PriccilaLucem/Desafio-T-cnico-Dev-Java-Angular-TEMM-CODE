import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductsService } from '../services/products.service';
import { Product } from 'app/interfaces/product.interface';
import { CommonModule } from '@angular/common';
import { ProductCreateComponent } from 'app/product-create/product-create.component';
import { ProductCreateService } from 'app/services/product-create.service';
import { ProductUpdateService } from 'app/services/product-update.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, FormsModule, ProductCreateComponent],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products$!: Observable<Product[]>;
  editingId: number | null = null;
  updatedName: string = '';
  updatedDescription: string = '';
  updatedPrice: number | null = null;
  updatedQuantity: number | null = null;
  updatedCategoryName: string = '';

  constructor(
    private service: ProductsService,
    private productUpdateService: ProductUpdateService,
    private _dialog: ProductCreateService
  ) {}

  ngOnInit(): void {
    this.loadProducts();
    this.productUpdateService.productUpdated$.subscribe(() => this.loadProducts());
  }

  loadProducts(): void {
    this.products$ = this.service.getProducts();
  }

  deleteProduct(id: number): void {
    this.service.deleteProduct(id).subscribe({
      next: () => {
        this.loadProducts();
      },
      error: (error) => {
        console.error('Error deleting product:', error);
      }
    });
  }

  enableEditing(product: Product): void {
    this.editingId = product.id;
    this.updatedName = product.nome;
    this.updatedDescription = product.descricao;
    this.updatedPrice = product.preco;
    this.updatedQuantity = product.quantity;
    this.updatedCategoryName = product.category.nome;
  }

  saveProduct(product: Product): void {
    product.nome = this.updatedName;
    product.descricao = this.updatedDescription;
    product.preco = this.updatedPrice!;
    product.quantity = this.updatedQuantity!;
    product.category.nome = this.updatedCategoryName;
    this.editingId = null;

    this.service.putProduct(product.id, product).subscribe({
      next: () => {
        this.loadProducts();
      },
      error: (error) => {
        console.error('Error updating product:', error);
      }
    });
  }

  cancelEditing(): void {
    this.editingId = null;
  }

  openProductCreate(): void {
    this._dialog.open({});
  }
}
