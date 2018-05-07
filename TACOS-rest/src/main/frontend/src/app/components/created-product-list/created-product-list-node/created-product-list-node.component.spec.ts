import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatedProductListNodeComponent } from './created-product-list-node.component';

describe('CreatedProductListNodeComponent', () => {
  let component: CreatedProductListNodeComponent;
  let fixture: ComponentFixture<CreatedProductListNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatedProductListNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatedProductListNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
