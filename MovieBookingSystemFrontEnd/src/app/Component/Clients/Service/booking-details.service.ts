import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookingDetailsService {

  BookingDetails : any = {
    ShowDetails : {}, 
    UserDetails: {}
  }
  constructor() { }

  updateShowDetails(show: any) {
    this.BookingDetails.ShowDetails = show;
  }
  updateUserDetails(user: any) {
    this.BookingDetails.UserDetails = user;
  }

  getBookingDetails() {
    return this.BookingDetails;
  }
}
