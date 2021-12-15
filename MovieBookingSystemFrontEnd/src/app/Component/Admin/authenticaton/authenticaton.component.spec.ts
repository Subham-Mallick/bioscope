import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthenticatonComponent } from './authenticaton.component';

describe('AuthenticatonComponent', () => {
  let component: AuthenticatonComponent;
  let fixture: ComponentFixture<AuthenticatonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AuthenticatonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthenticatonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
