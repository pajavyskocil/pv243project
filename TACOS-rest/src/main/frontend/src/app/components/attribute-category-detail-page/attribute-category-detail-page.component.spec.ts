import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeCategoryDetailPageComponent } from './attribute-category-detail-page.component';

describe('AttributeCategoryDetailPageComponent', () => {
  let component: AttributeCategoryDetailPageComponent;
  let fixture: ComponentFixture<AttributeCategoryDetailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeCategoryDetailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeCategoryDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
