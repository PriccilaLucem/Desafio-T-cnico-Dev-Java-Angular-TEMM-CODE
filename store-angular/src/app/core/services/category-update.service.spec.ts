import { TestBed } from '@angular/core/testing';
import { CategoryUpdateService } from './category-update.service';

describe('CategoryUpdateService', () => {
  let service: CategoryUpdateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryUpdateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should emit category updated event', (done: DoneFn) => {
    service.categoryUpdated$.subscribe(() => {
      expect(true).toBeTrue();
      done();
    });
    service.emitCategoryUpdated();
  });
});
