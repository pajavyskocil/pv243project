import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeCategoryListNodeComponent } from './attribute-category-list-node.component';

describe('AttributeCategoryListNodeComponent', () => {
  let component: AttributeCategoryListNodeComponent;
  let fixture: ComponentFixture<AttributeCategoryListNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeCategoryListNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeCategoryListNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
