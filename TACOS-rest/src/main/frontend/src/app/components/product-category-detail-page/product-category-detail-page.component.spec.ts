import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCategoryDetailPageComponent } from './product-category-detail-page.component';

describe('ProductCategoryDetailPageComponent', () => {
  let component: ProductCategoryDetailPageComponent;
  let fixture: ComponentFixture<ProductCategoryDetailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductCategoryDetailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCategoryDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
