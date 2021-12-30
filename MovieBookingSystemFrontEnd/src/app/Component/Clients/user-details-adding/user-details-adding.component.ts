import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { BookingDetailsService } from '../Service/booking-details.service';

@Component({
  selector: 'app-user-details-adding',
  templateUrl: './user-details-adding.component.html',
  styleUrls: ['./user-details-adding.component.scss']
})
export class UserDetailsAddingComponent implements OnInit {

  name = new FormControl('');
  armyNo = new FormControl('');
  phno = new FormControl('', )
  constructor( private bookingService: BookingDetailsService) {
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
    console.log('Save User Details')
    this.bookingService.updateUserDetails({})
  }


}
