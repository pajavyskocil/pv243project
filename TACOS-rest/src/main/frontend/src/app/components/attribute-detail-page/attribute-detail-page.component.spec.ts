import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeDetailPageComponent } from './attribute-detail-page.component';

describe('AttributeDetailPageComponent', () => {
  let component: AttributeDetailPageComponent;
  let fixture: ComponentFixture<AttributeDetailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeDetailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
