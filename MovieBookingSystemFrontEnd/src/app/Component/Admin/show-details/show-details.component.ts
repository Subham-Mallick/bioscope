import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddEditShowComponent } from './Dialog/add-edit-show/add-edit-show.component';

@Component({
  selector: 'app-show-details',
  templateUrl: './show-details.component.html',
  styleUrls: ['./show-details.component.scss']
})
export class ShowDetailsComponent implements OnInit {

  constructor(public dialog: MatDialog) {

  }

 ngOnInit(): void {
 }

 openDialog(mode: string): void {
   const dialogRef = this.dialog.open(AddEditShowComponent, {
     data: {
       mode: mode
     },
   });

   dialogRef.afterClosed().subscribe(result => {
     console.log('The dialog was closed');
   });
 }
}
