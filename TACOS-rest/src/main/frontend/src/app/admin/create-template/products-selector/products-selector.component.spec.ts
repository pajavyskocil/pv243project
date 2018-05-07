import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsSelectorComponent } from './products-selector.component';

describe('ProductsSelectorComponent', () => {
  let component: ProductsSelectorComponent;
  let fixture: ComponentFixture<ProductsSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProductsSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductsSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
