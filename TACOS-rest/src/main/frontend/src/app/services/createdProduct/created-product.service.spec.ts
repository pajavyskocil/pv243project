import { TestBed, inject } from '@angular/core/testing';

import { CreatedProductService } from './created-product.service';

describe('CreatedProductService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CreatedProductService]
    });
  });

  it('should be created', inject([CreatedProductService], (service: CreatedProductService) => {
    expect(service).toBeTruthy();
  }));
});
