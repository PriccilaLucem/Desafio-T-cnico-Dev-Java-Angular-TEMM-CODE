import { TestBed } from '@angular/core/testing';

import { CategoryCreateService } from './category-create.service';

describe('CategoryCreateService', () => {
  let service: CategoryCreateService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoryCreateService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
