import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';


export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}

@Component({
  selector: 'app-show-guest-list',
  templateUrl: './show-guest-list.component.html',
  styleUrls: ['./show-guest-list.component.scss']
})
export class ShowGuestListComponent implements OnInit, OnChanges {

  @Input() bookings : any[] = []
  displayError = true;
  
  dataSource : any = new MatTableDataSource(this.bookings);
  displayedColumns: string[] = ['Army', 'Name', 'Seats'];
  constructor() { }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.bookings == null || this.bookings.length == 0) {
      this.displayError = true;
      return;
    }
     
    this.displayError = false;

      console.log(this.bookings)
      this.bookings = this.bookings.map((item: any) => {
        return {
          ...item,
          bookedSeats: item.bookedSeats.map((item: any) => item.seatId).join(", ")
        }
      })
      this.dataSource = new MatTableDataSource(this.bookings)
  }
  


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
