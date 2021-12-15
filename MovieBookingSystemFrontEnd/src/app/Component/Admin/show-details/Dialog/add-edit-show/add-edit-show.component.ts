import { Component, OnInit, Inject } from '@angular/core';
import {  MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-edit-show',
  templateUrl: './add-edit-show.component.html',
  styleUrls: ['./add-edit-show.component.scss']
})
export class AddEditShowComponent implements OnInit {

  
  constructor(public dialogRef: MatDialogRef<AddEditShowComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

}
