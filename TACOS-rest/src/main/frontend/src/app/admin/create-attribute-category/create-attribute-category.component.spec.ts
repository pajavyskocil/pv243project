import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAttributeCategoryComponent } from './create-attribute-category.component';

describe('CreateAttributeCategoryComponent', () => {
  let component: CreateAttributeCategoryComponent;
  let fixture: ComponentFixture<CreateAttributeCategoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateAttributeCategoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateAttributeCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
