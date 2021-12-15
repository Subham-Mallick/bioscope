import { Component, OnInit, Inject } from '@angular/core';
import {  MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-movie-edit-add',
  templateUrl: './movie-edit-add.component.html',
  styleUrls: ['./movie-edit-add.component.scss']
})
export class MovieEditAddComponent implements OnInit {

  public movieName: String = ""
  public movieDescription: String = ""
  constructor(public dialogRef: MatDialogRef<MovieEditAddComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }
  
  ngOnInit(): void {
  }

  saveMovieData() {
    console.log('Save is Working')
    
    var movieDetails = {
      movieId: this.assignIdToMovie(),
      movieName: this.movieName,
      movieDescription: this.movieDescription
    }
    if(this.data.mode == 'Add') movieDetails.movieId = this.generateGuid();
    this.dialogRef.close(movieDetails)

  }

  assignIdToMovie() {
    if(this.data.mode == 'Add') return this.generateGuid();
    return this.data.movieDetails.movieId;
  }
  
  generateGuid() : String {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = Math.random() * 16 | 0,
        v = c == 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
    });
  }



}
