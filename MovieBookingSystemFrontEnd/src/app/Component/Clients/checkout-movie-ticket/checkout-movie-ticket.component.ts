import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-checkout-movie-ticket',
  templateUrl: './checkout-movie-ticket.component.html',
  styleUrls: ['./checkout-movie-ticket.component.scss']
})
export class CheckoutMovieTicketComponent implements OnInit {

  name: string = 'Arnab Hazra';
  seats: string[] = ['A12', 'B13', 'C24'];
  date: Date = new Date();

  constructor() { }

  ngOnInit(): void {
  }
  

}
