import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TemplateListNodeComponent } from './template-list-node.component';

describe('TemplateListNodeComponent', () => {
  let component: TemplateListNodeComponent;
  let fixture: ComponentFixture<TemplateListNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TemplateListNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemplateListNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
