import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeCategorySelectorComponent } from './attribute-category-selector.component';

describe('AttributeCategorySelectorComponent', () => {
  let component: AttributeCategorySelectorComponent;
  let fixture: ComponentFixture<AttributeCategorySelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeCategorySelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeCategorySelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
