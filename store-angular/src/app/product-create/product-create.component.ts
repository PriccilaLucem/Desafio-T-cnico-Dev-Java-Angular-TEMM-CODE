import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductsService } from '../services/products.service'; // Certifique-se de ajustar o caminho conforme necessário
import { Product } from '../interfaces/product.interface'; // Certifique-se de ajustar o caminho conforme necessário
import { ProductCreateService } from 'app/services/product-create.service';
import { ProductUpdateService } from 'app/services/product-update.service';

@Component({
  selector: 'app-product-create',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
  showError: boolean = false;
  productForm!: FormGroup;
  constructor(private fb: FormBuilder,
    private productService: ProductsService,
    private product: ProductCreateService,
    private productUpdateService: ProductUpdateService
  ) {}

  ngOnInit(): void {
    this.productForm = this.fb.group({
      nome: ['', Validators.required],
      descricao: ['', Validators.required],
      preco: ['', [Validators.required, Validators.min(0)]],
      quantity: ['', [Validators.required, Validators.min(0)]],
      categoryNome: ['', Validators.required]
    });
  }

  onSubmit(): void {

    if (this.productForm.valid) {
      const product: Product = this.productForm.value;
      this.productService.postProduct(product).subscribe({
        next: (response) => {
            this.productUpdateService.emitProductUpdate() 
            this.close();
        },
        error: (error) => {
          this.showError = true;
        }
      });
    }
  }
  close(): void{
    this.product.open({})
  }
}
