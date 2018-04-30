import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersListNodeComponent } from './users-list-node.component';

describe('UsersListNodeComponent', () => {
  let component: UsersListNodeComponent;
  let fixture: ComponentFixture<UsersListNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsersListNodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersListNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
