import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCategoriesPageComponent } from './product-categories-page.component';

describe('ProductCategoriesPageComponent', () => {
  let component: ProductCategoriesPageComponent;
  let fixture: ComponentFixture<ProductCategoriesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductCategoriesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCategoriesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
