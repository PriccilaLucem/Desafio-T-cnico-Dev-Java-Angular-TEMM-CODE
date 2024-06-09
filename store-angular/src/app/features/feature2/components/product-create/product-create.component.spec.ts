import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProductCreateComponent } from './product-create.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ProductsService } from 'app/features/feature1/services/products.service'; 
import { ProductCreateService } from '../../services/product-create.service';
import { ProductUpdateService } from 'app/core/services/product-update.service';
import { from, of, throwError } from 'rxjs';
import { Product } from 'app/interfaces/product.interface';

describe('ProductCreateComponent', () => {
  let component: ProductCreateComponent;
  let fixture: ComponentFixture<ProductCreateComponent>;
  let productServiceSpy: jasmine.SpyObj<ProductsService>;
  let productCreateServiceSpy: jasmine.SpyObj<ProductCreateService>;
  let productUpdateServiceSpy: jasmine.SpyObj<ProductUpdateService>;

  beforeEach(async () => {
    const productServiceSpyObj = jasmine.createSpyObj('ProductsService', ['postProduct']);
    const productCreateServiceSpyObj = jasmine.createSpyObj('ProductCreateService', ['open']);
    const productUpdateServiceSpyObj = jasmine.createSpyObj('ProductUpdateService', ['emitProductUpdate']);

    await TestBed.configureTestingModule({
      imports: [ ReactiveFormsModule ],
      providers: [
        { provide: ProductsService, useValue: productServiceSpyObj },
        { provide: ProductCreateService, useValue: productCreateServiceSpyObj },
        { provide: ProductUpdateService, useValue: productUpdateServiceSpyObj }
      ]
    })
    .compileComponents();

    productServiceSpy = TestBed.inject(ProductsService) as jasmine.SpyObj<ProductsService>;
    productCreateServiceSpy = TestBed.inject(ProductCreateService) as jasmine.SpyObj<ProductCreateService>;
    productUpdateServiceSpy = TestBed.inject(ProductUpdateService) as jasmine.SpyObj<ProductUpdateService>;
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set showError to true on form submission error', () => {
    const productSent = {
      nome: 'Test Product',
      descricao: 'Test Description',
      preco: 10,
      quantity: 5,
      categoryNome: "bla"
    };
    productServiceSpy.postProduct.and.returnValue(from(Promise.reject({ response: { status: 400 } })));
    component.productForm.setValue(productSent);

    component.onSubmit();


    setTimeout(() => {
      expect(component.showError).toBeTrue();
      
    }, 0);
  });
  it('should emit product update and call close method on successful form submission', () => {
    const returnableProduct: Product= {
      id: 1,
      nome: 'Test Product',
      descricao: 'Test Description',
      preco: 10,
      quantity: 5,
      category: {
        descricao: "bla",
        id: 1,
        nome: "bla"
      }
    };
    
    const productSent = {
      nome: 'Test Product',
      descricao: 'Test Description',
      preco: 10,
      quantity: 5,
      categoryNome: "bla"
    };
    
    productServiceSpy.postProduct.and.returnValue(of(returnableProduct));
    component.productForm.setValue(productSent);
    spyOn(component, 'close').and.callThrough();
  
    component.onSubmit();
  
    expect(productUpdateServiceSpy.emitProductUpdate).toHaveBeenCalled();
    expect(component.close).toHaveBeenCalled();
  });
  
  
});
