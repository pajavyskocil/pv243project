import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributesSelectorComponent } from './attributes-selector.component';

describe('AttributesSelectorComponent', () => {
  let component: AttributesSelectorComponent;
  let fixture: ComponentFixture<AttributesSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributesSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributesSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
