import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeCategoryDetailComponent } from './attribute-category-detail.component';

describe('AttributeCategoryDetailComponent', () => {
  let component: AttributeCategoryDetailComponent;
  let fixture: ComponentFixture<AttributeCategoryDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeCategoryDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeCategoryDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
