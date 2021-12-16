import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import {  MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { MovieService } from '../../../add-movie/services/movie.service';

@Component({
  selector: 'app-add-edit-show',
  templateUrl: './add-edit-show.component.html',
  styleUrls: ['./add-edit-show.component.scss']
})
export class AddEditShowComponent implements OnInit {

  showDetails: FormGroup;
  movies: any[] = []
  filteredOptions: Observable<any[]>;

  constructor(private formBuilder: FormBuilder, public dialogRef: MatDialogRef<AddEditShowComponent>,
    private movieService: MovieService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
        this.showDetails = new FormGroup({
          showId: new FormControl(data.showDetails.showId),
          movieDetails: new FormControl(data.showDetails.movieDetails),
          showDateTime: new FormControl(data.showDetails.showDateTime),
          bookingAvailable: new FormControl(data.showDetails.bookingAvailable),
          bookings: new FormControl(data.showDetails.bookings),
          availableSeats: new FormControl(data.showDetails.availableSeats)
        })

        this.filteredOptions = new Observable()
    }

  ngOnInit(): void {
    console.log(this.showDetails.value)
    this.movieService.getAllmovies().subscribe((response: any) => {
      this.movies = response
    })

  }

  saveShowDetails() {
    if(this.showDetails.pristine == true) this.closeDialog();
    this.dialogRef.close(this.showDetails.value);
  }

  closeDialog() {
    this.dialogRef.close();
  }

  generateGuid() : String {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = Math.random() * 16 | 0,
        v = c == 'x' ? r : (r & 0x3 | 0x8);
      return v.toString(16);
    });
  }

}
