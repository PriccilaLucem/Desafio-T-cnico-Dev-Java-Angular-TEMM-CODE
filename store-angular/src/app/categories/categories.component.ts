import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Category } from 'app/interfaces/category.interface';
import { CategoriesService } from 'app/services/categories.service';
import { CategoryCreateService } from 'app/services/category-create.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-categories',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.css'
})
export class CategoriesComponent implements OnInit {
  categories$!: Observable<Category[]>;

  constructor(private service: CategoriesService, private _dialog: CategoryCreateService){}

  ngOnInit(){
   this.categories$ = this.service.getCategories()
  }
  openCategoryCrate(){
    this._dialog.open({})
  }

}
