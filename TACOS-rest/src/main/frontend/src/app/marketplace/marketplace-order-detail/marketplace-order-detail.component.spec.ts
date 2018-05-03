import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MarketplaceOrderDetailComponent } from './marketplace-order-detail.component';

describe('MarketplaceOrderDetailComponent', () => {
  let component: MarketplaceOrderDetailComponent;
  let fixture: ComponentFixture<MarketplaceOrderDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MarketplaceOrderDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MarketplaceOrderDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
