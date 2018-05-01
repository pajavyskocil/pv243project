import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BasketProductListNodeComponent } from './basket-product-list-node.component';

describe('BasketProductListNodeComponent', () => {
  let component: BasketProductListNodeComponent;
  let fixture: ComponentFixture<BasketProductListNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BasketProductListNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BasketProductListNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
