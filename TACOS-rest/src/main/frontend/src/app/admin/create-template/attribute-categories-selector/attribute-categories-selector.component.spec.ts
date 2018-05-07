import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeCategoriesSelectorComponent } from './attribute-categories-selector.component';

describe('AttributeCategoriesSelectorComponent', () => {
  let component: AttributeCategoriesSelectorComponent;
  let fixture: ComponentFixture<AttributeCategoriesSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeCategoriesSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeCategoriesSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
