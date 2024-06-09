import { TestBed } from '@angular/core/testing';
import { MatDialog } from '@angular/material/dialog';
import { CategoryCreateService } from './category-create.service';
import { CategoryCreateComponent } from '../components/category-create/category-create.component';
describe('CategoryCreateService', () => {
  let service: CategoryCreateService;
  let dialogSpy: jasmine.SpyObj<MatDialog>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('MatDialog', ['open', 'closeAll']);

    TestBed.configureTestingModule({
      providers: [
        CategoryCreateService,
        { provide: MatDialog, useValue: spy }
      ]
    });

    service = TestBed.inject(CategoryCreateService);
    dialogSpy = TestBed.inject(MatDialog) as jasmine.SpyObj<MatDialog>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should open dialog when not already open', () => {
    service.is_open = false;
    service.open({});
    expect(dialogSpy.open).toHaveBeenCalledOnceWith(CategoryCreateComponent, {
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
