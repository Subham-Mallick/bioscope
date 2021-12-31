import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingDetailsService } from '../Service/booking-details.service';

@Component({
  selector: 'app-user-details-adding',
  templateUrl: './user-details-adding.component.html',
  styleUrls: ['./user-details-adding.component.scss']
})
export class UserDetailsAddingComponent implements OnInit {

  name = new FormControl('');
  armyNo = new FormControl('');
  seats = new FormControl(1);
  constructor( private bookingService: BookingDetailsService,
    private activateRoute: ActivatedRoute, private _snackBar: MatSnackBar,
    private router: Router) {
    this.minDate = this.getMonday(new Date());
    this.maxDate = this.getSunday(new Date());
   }
  minDate: Date;
  maxDate: Date;

  ngOnInit(): void {
    
  }

  dateFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    // Prevent Saturday and Sunday from being selected.
    return day !== 2;
  };

  getSunday(d: Date): Date {
    d = new Date(d);
    var day = d.getDay(),
        diff = d.getDate() - day + (day == 0 ? -6:1); // adjust when day is sunday
    return new Date(d.setDate(diff+6-2));
  }


  getMonday(d: Date): Date {
    d = new Date(d);
    var day = d.getDay(),
        diff = d.getDate() - day + (day == 0 ? -6:1)-2; // adjust when day is sunday
    return new Date(d.setDate(diff));
  }

  saveUserDetails() {

    if(this.isInvalidSubmission()) {
      this.openSnackBar();
      return;
    }
    console.log('Save User Details')
    let userDetails = {
      bookedUserName: this.name.value,
      bookedUserArmyNumber: this.armyNo.value,
      showId: this.activateRoute.snapshot.paramMap.get("showId"),
      seats: this.seats.value
    }

    console.log(userDetails)
    this.bookingService.updateBookingDetails(userDetails)
    this.router.navigateByUrl("client/theaterSeat")
  }

  isInvalidSubmission() {
    if(this.name.value === '' || this.armyNo.value === true) return true;
    return false;
  }

  openSnackBar() {
    this._snackBar.open("Please Enter Valid Details", '', {
      duration: 3000,
    });
  }


}
