import { TestBed } from '@angular/core/testing';
import { ProductUpdateService } from './product-update.service';

describe('ProductUpdateService', () => {
  let service: ProductUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should emit product update', () => {
    let emitted = false;

    service.productUpdated$.subscribe(() => {
      emitted = true;
    });

    service.emitProductUpdate();
    expect(emitted).toBeTrue();
  });
});
