import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common'

@Component({
  selector: 'app-checkout-movie-ticket',
  templateUrl: './checkout-movie-ticket.component.html',
  styleUrls: ['./checkout-movie-ticket.component.scss']
})
export class CheckoutMovieTicketComponent implements OnInit {

  name: string = '';
  seats: string[] = [];
  date: Date = new Date();
  time: string = ''
  movieName: string = ''
  bookingDetails: any = {}

  constructor(private location: Location) { }

  ngOnInit(): void {
    console.log(this.location.getState())
    this.bookingDetails = this.location.getState();
    this.seats = this.bookingDetails.bookedSeats.map((booking: any) => booking.seatId)
    this.name = this.bookingDetails.bookedUserName;
    this.date = this.bookingDetails.show.showDateTime.split("T")[0]
    this.time = this.bookingDetails.show.showDateTime.split("T")[1]
    this.movieName = this.bookingDetails.show.movieDetails.movieName
  }
  

}
