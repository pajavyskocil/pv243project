import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeCategoryListComponent } from './attribute-category-list.component';

describe('AttributeCategoryListComponent', () => {
  let component: AttributeCategoryListComponent;
  let fixture: ComponentFixture<AttributeCategoryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeCategoryListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeCategoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
