import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { CategoryCreateComponent } from './category-create.component';
import { CategoriesService } from 'app/features/feature1/services/categories.service';
import { CategoryCreateService } from '../../services/category-create.service';
import { CategoryUpdateService } from 'app/core/services/category-update.service';
import { from, of, throwError } from 'rxjs';

describe('CategoryCreateComponent', () => {
  let component: CategoryCreateComponent;
  let fixture: ComponentFixture<CategoryCreateComponent>;
  let categoriesServiceSpy: jasmine.SpyObj<CategoriesService>;
  let categoryCreateServiceSpy: jasmine.SpyObj<CategoryCreateService>;
  let categoryUpdateServiceSpy: jasmine.SpyObj<CategoryUpdateService>;

  beforeEach(async () => {
    categoriesServiceSpy = jasmine.createSpyObj('CategoriesService', ['postCategory']);
    categoryCreateServiceSpy = jasmine.createSpyObj('CategoryCreateService', ['open']);
    categoryUpdateServiceSpy = jasmine.createSpyObj('CategoryUpdateService', ['emitCategoryUpdated']);

    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, CategoryCreateComponent],
      providers: [
        { provide: CategoriesService, useValue: categoriesServiceSpy },
        { provide: CategoryCreateService, useValue: categoryCreateServiceSpy },
        { provide: CategoryUpdateService, useValue: categoryUpdateServiceSpy }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize form on ngOnInit', () => {
    expect(component.categoryForm).toBeDefined();
    expect(component.categoryForm.get('nome')).toBeDefined();
    expect(component.categoryForm.get('descricao')).toBeDefined();
  });

  it('should emit category updated and close dialog on successful form submission', () => {
    const category = { nome: 'Test Category', descricao: 'Test Description', id: 1 };
    const categorySent = { nome: 'Test Category', descricao: 'Test Description' };
    categoriesServiceSpy.postCategory.and.returnValue(of(category));
    
    component.categoryForm.setValue(categorySent)
    component.onSubmit();
    
    expect(categoriesServiceSpy.postCategory).toHaveBeenCalledWith(jasmine.objectContaining(categorySent));
    expect(categoryUpdateServiceSpy.emitCategoryUpdated).toHaveBeenCalled();
    expect(categoryCreateServiceSpy.open).toHaveBeenCalled();
  });
  
  
  
  it('should set showError to true on form submission error', () => {
    const categorySent = { nome: 'Test Category', descricao: 'Test Description' };
    
    categoriesServiceSpy.postCategory.and.returnValue(from(Promise.reject({ response: { status: 400 } })));
    
    component.categoryForm.setValue(categorySent);
  
    component.onSubmit();
    
    setTimeout(() => {
      expect(component.showError).toBeTruthy();
    }, 0);
  });
  
  
  

  it('should close dialog on close method call', () => {
    component.close();
    expect(categoryCreateServiceSpy.open).toHaveBeenCalled();
  });
});
