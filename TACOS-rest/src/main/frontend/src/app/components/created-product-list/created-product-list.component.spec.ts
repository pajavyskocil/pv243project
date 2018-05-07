import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatedProductListComponent } from './created-product-list.component';

describe('CreatedProductListComponent', () => {
  let component: CreatedProductListComponent;
  let fixture: ComponentFixture<CreatedProductListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreatedProductListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatedProductListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
