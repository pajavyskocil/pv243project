import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TemplateDetailPageComponent } from './template-detail-page.component';

describe('TemplateDetailPageComponent', () => {
  let component: TemplateDetailPageComponent;
  let fixture: ComponentFixture<TemplateDetailPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TemplateDetailPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemplateDetailPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
