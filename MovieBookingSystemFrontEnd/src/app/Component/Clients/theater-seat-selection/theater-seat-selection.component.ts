import { Component, OnInit } from '@angular/core';
import { BookingDetailsService } from '../Service/booking-details.service';

@Component({
  selector: 'app-theater-seat-selection',
  templateUrl: './theater-seat-selection.component.html',
  styleUrls: ['./theater-seat-selection.component.scss']
})
export class TheaterSeatSelectionComponent implements OnInit {

  constructor(private bookingService: BookingDetailsService) { }

  
  totalSeats : any [] = []
  availableSeats : any [] = []
  selectedSeats: any [] = []
  rowLengths : number[] = [18, 14, 24, 24, 24, 24, 24, 24, 22, 19]
  BookingDetails : any = {}
  count = 0
  ngOnInit(): void {
    this.bookingService.getBookingDetails().subscribe(response => {
      this.BookingDetails = response;
      console.log(response)
    })
    this.dummyInitialisation()
  }


  dummyInitialisation () {
    for(let  i = 0; i < this.rowLengths.length; i++) {
      for(let j = 0; j < this.rowLengths[i]; j++) {
        this.totalSeats.push({
          seatId: String.fromCharCode('J'.charCodeAt(0)-i)+(j+1),
          rows: i+1,
          cloumn: j+1,
          blocked: false,
          selected: false
        })
      }
    }

    console.log(this.totalSeats.length)
  }

  selectSeat(index: number) {
    if(this.totalSeats[index].blocked === true || this.BookingDetails === null)  return;
    else if(this.totalSeats[index].selected == false && this.BookingDetails.seats === this.count) return;
    this.totalSeats[index].selected = !this.totalSeats[index].selected;
    if(this.totalSeats[index].selected == true) this.count++;
    else this.count--;
    console.log(this.count)
  }

}
