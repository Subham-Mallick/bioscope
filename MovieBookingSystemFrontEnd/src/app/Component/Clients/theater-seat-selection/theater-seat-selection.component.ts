import { Component, OnInit } from '@angular/core';
import { BookingDetailsService } from '../Service/booking-details.service';
import { ShowDetailsClientService } from '../show-details-client/service/show-details-client.service';
import { ShowDetailsClientComponent } from '../show-details-client/show-details-client.component';
import { BlockedSeatsService } from './service/blocked-seats.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-theater-seat-selection',
  templateUrl: './theater-seat-selection.component.html',
  styleUrls: ['./theater-seat-selection.component.scss']
})
export class TheaterSeatSelectionComponent implements OnInit {

  constructor(private bookingService: BookingDetailsService, private blockedSeatService: BlockedSeatsService,
    private router : Router,
    private _snackBar: MatSnackBar ) { }

  movie: any = {}
  showMovieName: boolean = false;
  show: any = {}
  totalSeats : any [] = []
  availableSeats : any [] = []
  selectedSeats: any [] = []
  rowLengths : number[] = [18, 14, 24, 24, 24, 24, 24, 24, 22, 19]
  BookingDetails : any = {

  }
  count = 0
  ngOnInit(): void {
    
   this.fetchBookingDetails();
    

  }

  fetchBookingDetails( ) {

    this.bookingService.getBookingDetails().subscribe(response => {
      this.BookingDetails = response;
      this.dummyInitialisation()
      console.log(response)
      this.showBookingDetails()
    })
  }

  showBookingDetails() {
    if(this.BookingDetails == null || this.BookingDetails == undefined) return;

    this.blockedSeatService.getShowDetails(this.BookingDetails.showId).subscribe((response : any) =>{
      this.availableSeats = response.availableSeats
      console.log("Show Details -->",response)
      this.show = response;
      this.movie = response.movieDetails;
      this.showMovieName = true
      this.setBlockedSeats()
    })
  }

  setBlockedSeats() {
    for(let i = 0; i < this.totalSeats.length; i++) {
      for(let j = 0; j < this.availableSeats.length; j++) {

        if(this.totalSeats[i].row == this.availableSeats[j].row && this.totalSeats[i].column == this.availableSeats[j].column) {
          this.totalSeats[i].blocked = false;
   
          break;
        }

      }
    }
  }

  dummyInitialisation () {
    for(let  i = 0; i < this.rowLengths.length; i++) {
      for(let j = 0; j < this.rowLengths[i]; j++) {
        this.totalSeats.push({
          seatId: String.fromCharCode('J'.charCodeAt(0)-i)+(j+1),
          row: i+1,
          column: j+1,
          blocked: true,
          selected: false
        })
      }
    }


  }

   
  
  selectSeat(index: number) {
    if(this.totalSeats[index].blocked === true || this.BookingDetails === null)  return;
    else if(this.totalSeats[index].selected == false && this.BookingDetails.seats === this.count) return;
    this.totalSeats[index].selected = !this.totalSeats[index].selected;
    if(this.totalSeats[index].selected == true) this.count++;
    else this.count--;
    console.log(this.count)
  }


  bookSeats() {

    this.addSelectedSeats();
    this.BookingDetails.bookedSeats = this.selectedSeats;

    console.log(this.BookingDetails)

    this.checkBookingAndConfirm() 

  }

  checkBookingAndConfirm() {
    this.blockedSeatService.getShowDetails(this.BookingDetails.showId).subscribe((response : any) =>{ 
      this.availableSeats = response.availableSeats
      this.checkConflict();
    })
  }

  checkConflict() {

    var booking = this.BookingDetails;
    delete booking.seats;
    
    booking.bookingId = ''
    console.log(booking)
    this.bookingService.saveBookingDetails(this.BookingDetails.showId, booking).subscribe((response => {
      this.openSnackBar("Booking ", "Successful")
      this.BookingDetails.show = this.show;
      this.router.navigateByUrl("/client/ticket", { state: this.BookingDetails})

    }), (error) => {
      console.log(error)
      this.openSnackBar("Booking", "Failed")
      this.fetchBookingDetails()
    })

  }

  

  addSelectedSeats() {
    this.selectedSeats = this.totalSeats.filter((seat: any) => seat.selected === true).map((seat: any) => {
      delete seat.selected;
      seat.blocked = true;
      return seat;
    });

    
    
    
  }

  
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 3000 });
  }

}
