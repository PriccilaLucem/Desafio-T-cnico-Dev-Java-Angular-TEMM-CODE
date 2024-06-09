import { TestBed } from '@angular/core/testing';
import { MatDialog } from '@angular/material/dialog';
import { ProductCreateService } from './product-create.service';
import { ProductCreateComponent } from '../components/product-create/product-create.component';

describe('ProductCreateService', () => {
  let service: ProductCreateService;
  let dialogSpy: jasmine.SpyObj<MatDialog>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('MatDialog', ['open', 'closeAll']);

    TestBed.configureTestingModule({
      providers: [
        ProductCreateService,
        { provide: MatDialog, useValue: spy }
      ]
    });

    service = TestBed.inject(ProductCreateService);
    dialogSpy = TestBed.inject(MatDialog) as jasmine.SpyObj<MatDialog>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should open dialog when not already open', () => {
    service.is_open = false;
    service.open({});
    expect(dialogSpy.open).toHaveBeenCalledOnceWith(ProductCreateComponent, {
      width: '50%',
      height: '50%',
      panelClass: 'pos-absolute'
    });
  });

  it('should close dialog when already open', () => {
    service.is_open = true;
    service.open({});
    expect(dialogSpy.closeAll).toHaveBeenCalled();
  });
});
