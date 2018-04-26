import { TestBed, inject } from '@angular/core/testing';

import { AttributeCategoryService } from './attribute-category.service';

describe('AttributeCategoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AttributeCategoryService]
    });
  });

  it('should be created', inject([AttributeCategoryService], (service: AttributeCategoryService) => {
    expect(service).toBeTruthy();
  }));
});
