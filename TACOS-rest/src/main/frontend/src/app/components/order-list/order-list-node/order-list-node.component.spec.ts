import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderListNodeComponent } from './order-list-node.component';

describe('OrderListNodeComponent', () => {
  let component: OrderListNodeComponent;
  let fixture: ComponentFixture<OrderListNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrderListNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderListNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
