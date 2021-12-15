import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieGuestDetailsComponent } from './movie-guest-details.component';

describe('MovieGuestDetailsComponent', () => {
  let component: MovieGuestDetailsComponent;
  let fixture: ComponentFixture<MovieGuestDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovieGuestDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieGuestDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
