import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckoutMovieTicketComponent } from './checkout-movie-ticket.component';

describe('CheckoutMovieTicketComponent', () => {
  let component: CheckoutMovieTicketComponent;
  let fixture: ComponentFixture<CheckoutMovieTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckoutMovieTicketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckoutMovieTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
