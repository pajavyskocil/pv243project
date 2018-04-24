import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDetailEmailComponent } from './user-detail-email.component';

describe('UserDetailEmailComponent', () => {
  let component: UserDetailEmailComponent;
  let fixture: ComponentFixture<UserDetailEmailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserDetailEmailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDetailEmailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
