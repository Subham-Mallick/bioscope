import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AddEditShowComponent } from './Dialog/add-edit-show/add-edit-show.component';
import { ShowService } from './services/show.service';

@Component({
  selector: 'app-show-details',
  templateUrl: './show-details.component.html',
  styleUrls: ['./show-details.component.scss']
})
export class ShowDetailsComponent implements OnInit {

  public shows: any[] = []
  private AllShows : any [] = []
  public booking : any = {}
  public showId: String = '';
  constructor(public dialog: MatDialog, private showService: ShowService, private _snackBar: MatSnackBar) {

  }

  ngOnInit(): void {
    this.getAllShows()
  }

  modifyShowDate() {

    this.shows = this.AllShows.map(show => {
      var date = new Date(show.showDateTime)
      var month = date.getMonth() + 1;
      var minutes = date.getMinutes();
      var hours = date.getHours();
      return {
        ...show,
        showDate : `${date.getDate() > 9 ? date.getDate() : '0'+date.getDate()}/${ month > 9 ? month : '0' + month}/${date.getFullYear()}`,
        showTime : `${hours > 9 ? hours: '0'+hours }:${minutes > 9 ? minutes : '0'+minutes}`
      }
    })
  }
  openDialog(mode: string, showDetails: any, index: number): void {
    const dialogRef = this.dialog.open(AddEditShowComponent, {
      data: {
        mode: mode,
        showDetails: this.AllShows[index]
      },
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result != undefined) {
        console.log(result)
        if(mode == 'Add')  this.addShow(result);
        else this.updateShow(result, index);
      }
    }); 
  }

  addShow(show: any) {
    this.showService.addNewShow(show).subscribe(response => {
      console.log(show)
      this.openSnackBar("New Show Created", "Success");
      this.getAllShows();
    }, error=>{
      this.openSnackBar("Unable to Create the Show", "Failed");
    })
  }

  updateShow(show: any, index: number) {
    this.showService.updateShow(show).subscribe(response=> {
      this.getAllShows();
      this.openSnackBar("Show Updated", "Success");
    }, (error) => {
      this.openSnackBar("Failed to Update the Show", "Failed")
    })
  }

  getAllShows() {
    this.AllShows = []
    this.showService.getAllShows().subscribe((response: any) => {
      console.log(response)
      this.AllShows = response;
      this.modifyShowDate()
    })
  }

  deleteShow(show: any) {
    this.showService.deleteShow(show).subscribe(response => {
      this.openSnackBar("Show Deleted", "Success")
      this.getAllShows()
    }, error => {
      this.openSnackBar("Unable to Delete Show", "Failed")
    })
  }

  fetchBookingDetails(show: any) {
    console.log(show)
    this.showId = show.showId;
    this.booking = show.bookings;

    console.log(this.showId, this.booking)

  }
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 3000 });
  }

}
