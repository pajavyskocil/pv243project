import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EshopCategoriesComponent } from './eshop-categories.component';

describe('EshopCategoriesComponent', () => {
  let component: EshopCategoriesComponent;
  let fixture: ComponentFixture<EshopCategoriesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EshopCategoriesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EshopCategoriesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
