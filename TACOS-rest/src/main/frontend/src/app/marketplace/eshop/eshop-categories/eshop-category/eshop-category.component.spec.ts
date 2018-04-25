import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EshopCategoryComponent } from './eshop-category.component';

describe('EshopCategoryComponent', () => {
  let component: EshopCategoryComponent;
  let fixture: ComponentFixture<EshopCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EshopCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EshopCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
