import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductCategoryListNodeComponent } from './product-category-list-node.component';

describe('ProductCategoryListNodeComponent', () => {
  let component: ProductCategoryListNodeComponent;
  let fixture: ComponentFixture<ProductCategoryListNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductCategoryListNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductCategoryListNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
