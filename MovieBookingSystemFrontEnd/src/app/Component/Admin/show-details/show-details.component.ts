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
  constructor(public dialog: MatDialog, private showService: ShowService, private _snackBar: MatSnackBar) {

  }

  ngOnInit(): void {
    this.getAllShows()
  }

  modifyShowDate() {
    this.shows = this.shows.map(show => {
      var date = new Date(show.showDateTime)
      return {
        ...show,
        showDate : `${date.getDate()}/${date.getMonth()}/${date.getFullYear()}`,
        showTime : `${date.getHours() }:${date.getMinutes()}`
      }
    })
  }
  openDialog(mode: string, showDetails: any, index: number): void {
    const dialogRef = this.dialog.open(AddEditShowComponent, {
      data: {
        mode: mode,
        showDetails
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
      this.shows.push(show);
      this.openSnackBar("New Show Created", "Success")
    }, error=>{
      this.openSnackBar("Unable to Create the Show", "Failed");
    })
  }

  updateShow(show: any, index: number) {
    this.showService.updateShow(show).subscribe(response=> {
      this.shows[index] = show;
      this.openSnackBar("Show Updated", "Success");
    }, (error) => {
      this.openSnackBar("Failed to Update the Show", "Failed")
    })
  }

  getAllShows() {
    this.shows = []
    this.showService.getAllShows().subscribe((response: any) => {
      console.log(response)
      this.shows = response;
      this.modifyShowDate()
    })
  }

  deleteShow(show: any) {
    this.showService.deleteShow(show).subscribe(response => {
      this.openSnackBar("Show Deleted", "Success")
    }, error => {
      this.openSnackBar("Unable to Delete Show", "Failed")
    })
  }

  fetchBookingDetails(show: any) {

  }
  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 3000 });
  }

}
