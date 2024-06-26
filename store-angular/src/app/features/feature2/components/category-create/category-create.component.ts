import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, Validators, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Category } from 'app/interfaces/category.interface';
import { CategoriesService } from 'app/features/feature1/services/categories.service';
import { CategoryCreateService } from '../../services/category-create.service';
import { CategoryUpdateService } from 'app/core/services/category-update.service';

@Component({
  selector: 'app-category-create',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CommonModule
  ],
  templateUrl: './category-create.component.html',
  styleUrl: './category-create.component.css'
})
export class CategoryCreateComponent {

  showError: boolean = false;
  categoryForm!: FormGroup;
  constructor(private fb: FormBuilder, private categoryService:CategoriesService, private categoryCrate: CategoryCreateService, private categoryUpdateService:CategoryUpdateService) {}

  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      nome: ['', Validators.required],
      descricao: ['', Validators.required],
    });
  }
  onSubmit(): void {
    if (this.categoryForm.valid) {
      const category: Category = this.categoryForm.value;
      this.categoryService.postCategory(category).subscribe({
        next: (response) => {
          this.categoryUpdateService.emitCategoryUpdated();
          this.close()
        },
        error: (error) => {
          this.showError = true;
        }
      });
    }
  }
  close(): void{
    this.categoryCrate.open({})
  }
}
