import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CategoriesComponent } from './categories.component';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CategoriesService } from 'app/features/feature1/services/categories.service';
import { CategoryCreateService } from 'app/features/feature2/services/category-create.service';
import { CategoryUpdateService } from 'app/core/services/category-update.service';
import { Observable, of } from 'rxjs';
import { Category } from 'app/interfaces/category.interface';

  
  describe('CategoriesComponent', () => {
    let component: CategoriesComponent;
    let fixture: ComponentFixture<CategoriesComponent>;
    let categoriesServiceSpy: jasmine.SpyObj<CategoriesService>;
    let categoryCreateServiceSpy: jasmine.SpyObj<CategoryCreateService>;
    let categoryUpdateServiceSpy: jasmine.SpyObj<CategoryUpdateService>;
    
    beforeEach(async () => {
      categoriesServiceSpy = jasmine.createSpyObj('CategoriesService', ['getCategories', 'deleteCategory', 'putCategory', 'filterByCaregoryName']);
      categoryCreateServiceSpy = jasmine.createSpyObj('CategoryCreateService', ['open']);
      categoryUpdateServiceSpy = jasmine.createSpyObj('CategoryUpdateService', ['categoryUpdated$']);
  
      categoryUpdateServiceSpy.categoryUpdated$ = of();
  
      await TestBed.configureTestingModule({
        imports: [
          CommonModule,
          FormsModule 
        ],
        providers: [
          { provide: CategoriesService, useValue: categoriesServiceSpy },
          { provide: CategoryCreateService, useValue: categoryCreateServiceSpy },
          { provide: CategoryUpdateService, useValue: categoryUpdateServiceSpy }
        ]
      })
      .compileComponents();
    });
    
    beforeEach(() => {
      fixture = TestBed.createComponent(CategoriesComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
    });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load categories on init', () => {
    const categories: Category[] = [{ id: 1, nome: 'Category 1', descricao: 'Description 1' }];
    categoriesServiceSpy.getCategories.and.returnValue(of(categories));

    component.ngOnInit();

    expect(categoriesServiceSpy.getCategories).toHaveBeenCalled();
    expect(component.categories$).toBeDefined();
  });

  it('should delete category', () => {
    const categoryId = 1;
    categoriesServiceSpy.deleteCategory.and.returnValue(new Observable<void>);

    component.deleteCategory(categoryId);

    expect(categoriesServiceSpy.deleteCategory).toHaveBeenCalledWith(categoryId);
  });

  it('should enable editing', () => {
    const category: Category = { id: 1, nome: 'Category 1', descricao: 'Description 1' };

    component.enableEditing(category);

    expect(component.editingId).toBe(category.id as number);
    expect(component.updatedName).toBe(category.nome);
    expect(component.updatedDescription).toBe(category.descricao);
  });

  it('should update category', () => {
    const category: Category = { id: 1, nome: 'Category 1', descricao: 'Description 1' };

    categoriesServiceSpy.putCategory.and.returnValue(of(category));

    component.saveCategory(category);

    expect(categoriesServiceSpy.putCategory).toHaveBeenCalledWith(category.id as number, category);
    expect(component.editingId).toBeNull();
  });

  it('should filter products by nome or categoryNome', () => {
    const searchTerm = 'test';
    const filteredCategories: Category[] = [{ id: 1, nome: 'test', descricao: 'Description 1' }];
    categoriesServiceSpy.filterByCaregoryName.and.returnValue(of(filteredCategories));

    component.searchTerm = searchTerm;
    component.filterProductsByNomeOrCategoryNome();

    expect(categoriesServiceSpy.filterByCaregoryName).toHaveBeenCalledWith(searchTerm);
    expect(component.categories$).toBeDefined();
  });

  it('should open category create dialog', () => {
    component.openCategoryCreateDialog();

    expect(categoryCreateServiceSpy.open).toHaveBeenCalled();
  });
});
