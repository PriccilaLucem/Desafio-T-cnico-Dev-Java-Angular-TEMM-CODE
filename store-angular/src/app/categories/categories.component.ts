import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Category } from 'app/interfaces/category.interface';
import { CategoriesService } from 'app/services/categories.service';
import { CategoryCreateService } from 'app/services/category-create.service';
import { CategoryUpdateService } from 'app/services/category-update.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-categories',
  standalone: true,
  imports: [
    CommonModule, 
    FormsModule
  ],
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.css'
})
export class CategoriesComponent implements OnInit {
  categories$!: Observable<Category[]>;
  editingId: number | null = null;
  updatedCategory: Category | null = null;
  updatedName: string = '';
  updatedDescription: string = '';

  constructor(
    private service: CategoriesService,
    private categoryUpdateService: CategoryUpdateService,
    private _dialog: CategoryCreateService
  ) {}

  ngOnInit(): void {
    this.loadCategories();

    this.categoryUpdateService.categoryUpdated$.subscribe(() => {
      this.loadCategories();
    });
  }

  loadCategories(): void {
    this.categories$ = this.service.getCategories();
  }

  deleteCategory(id: number): void {
    this.service.deleteCategory(id).subscribe({
      next: () => {
        this.loadCategories();
      },
      error: (error) => {
        console.error('Error deleting category:', error);
      }
    });
  }
  enableEditing(category: Category): void {
    this.editingId = category.id;
    this.updatedName = category.nome;
    this.updatedDescription = category.descricao;
  }

  saveCategory(category: Category): void {
    category.descricao = this.updatedDescription;
    category.nome = this.updatedName
    this.editingId = null;

    this.service.putCategory(category.id, category).subscribe({
      next: () => {
        this.loadCategories()
      }
    })
  }
  cancelEditing(): void {
    this.editingId = null;
  }
  openCategoryCreateDialog(){
    this._dialog.open({})
  }
}


