import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingDetailsService {

  BookingDetails : BehaviorSubject<any> = new BehaviorSubject(null)
  constructor() { }

  
  updateBookingDetails(user: any) {
    this.BookingDetails.next(user);
  }

  getBookingDetails() {
    return this.BookingDetails;
  }
}
