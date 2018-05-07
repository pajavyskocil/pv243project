import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeCategoriesPageComponent } from './attribute-categories-page.component';

describe('AttributeCategoriesPageComponent', () => {
  let component: AttributeCategoriesPageComponent;
  let fixture: ComponentFixture<AttributeCategoriesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeCategoriesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeCategoriesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
