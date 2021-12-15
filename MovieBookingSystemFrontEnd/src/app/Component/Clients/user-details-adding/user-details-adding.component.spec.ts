import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDetailsAddingComponent } from './user-details-adding.component';

describe('UserDetailsAddingComponent', () => {
  let component: UserDetailsAddingComponent;
  let fixture: ComponentFixture<UserDetailsAddingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDetailsAddingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDetailsAddingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
