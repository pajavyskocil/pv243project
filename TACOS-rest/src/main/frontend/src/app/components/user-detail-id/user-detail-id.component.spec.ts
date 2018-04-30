import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDetailIdComponent } from './user-detail-id.component';

describe('UserDetailIdComponent', () => {
  let component: UserDetailIdComponent;
  let fixture: ComponentFixture<UserDetailIdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDetailIdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDetailIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
