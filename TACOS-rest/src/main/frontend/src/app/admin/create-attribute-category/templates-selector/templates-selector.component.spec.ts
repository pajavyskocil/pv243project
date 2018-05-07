import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TemplatesSelectorComponent } from './templates-selector.component';

describe('TemplatesSelectorComponent', () => {
  let component: TemplatesSelectorComponent;
  let fixture: ComponentFixture<TemplatesSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TemplatesSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemplatesSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
