import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BookingDetailsService {

  BookingDetails : BehaviorSubject<any> = new BehaviorSubject(null)
  constructor(private httpClient: HttpClient) { }

  saveBookingDetails(showId: String, bookingDetails: any) {
    console.log(environment.api_url + environment.user_client + `${showId}/` + environment.bookings)
    return this.httpClient.post(environment.api_url + environment.user_client + `${showId}/` + environment.bookings, bookingDetails)
  }

  
  updateBookingDetails(user: any) {
    this.BookingDetails.next(user);
  }

  getBookingDetails() {
    return this.BookingDetails;
  }
}
