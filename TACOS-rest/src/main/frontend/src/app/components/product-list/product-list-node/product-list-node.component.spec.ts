import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductListNodeComponent } from './product-list-node.component';

describe('ProductListNodeComponent', () => {
  let component: ProductListNodeComponent;
  let fixture: ComponentFixture<ProductListNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductListNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductListNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
