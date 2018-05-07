import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeListNodeComponent } from './attribute-list-node.component';

describe('AttributeListNodeComponent', () => {
  let component: AttributeListNodeComponent;
  let fixture: ComponentFixture<AttributeListNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeListNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeListNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
