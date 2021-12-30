import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import {  MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { MovieService } from '../../../add-movie/services/movie.service';
import {map, startWith} from 'rxjs/operators';

@Component({
  selector: 'app-add-edit-show',
  templateUrl: './add-edit-show.component.html',
  styleUrls: ['./add-edit-show.component.scss']
})
export class AddEditShowComponent implements OnInit {

  movieName = new FormControl('');
  dateTime = new FormControl();
  movies = []
  options : any[] = []
  filteredOptions: Observable<string[]>;
  showDetails:any = {}
  bookingAvailable = new FormControl(false);
  constructor(private formBuilder: FormBuilder, public dialogRef: MatDialogRef<AddEditShowComponent>,
    private movieService: MovieService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.filteredOptions = new Observable();
      this.movieService.getAllmovies().subscribe((response: any) => {
        this.movies = response
        this.options = this.mapToOptions();
        this.filteredOptions = this.movieName.valueChanges.pipe(
          startWith(''),
          map(value => this._filter(value)),
        );
      })
      
    }

  ngOnInit(): void {
    

    this.fetchShowDetails()

  }




  fetchShowDetails() {
    var mode = this.data.mode;
    console.log(this.data)

    if(mode == 'Add') return;
    this.movieName.setValue(this.data.showDetails.movieDetails.movieName);
    this.dateTime.setValue(this.data.showDetails.showDateTime);
    this.bookingAvailable.setValue(this.data.showDetails.bookingAvailable)
  }
 
  addMovieObject() {
    var x = this.movies.findIndex((x: any) => x.movieName === this.movieName.value);
    console.log(x);
    return x;
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }

  mapToOptions() {
    var option = this.movies.map((movie:any) => movie.movieName);
    return option; 
  }

  saveShowDetails() {
    // this.dialogRef.close(this.showDetails);
    if(this.isValidSubmission() == false) this.closeDialog();
    
    console.log(this.bookingAvailable.value)
    this.showDetails = {
      showId: this.data.mode == 'Add' ? this.generateGuid() : this.data.showDetails.showId,
      bookings: this.data.showDetails === undefined ? null : this.data.showDetails.bookings,
      availableSeats: this.data.showDetails === undefined ? null : this.data.showDetails.availableSeats,
      showDateTime: this.dateTime.value,
      bookingAvailable: this.bookingAvailable.value,
      movieDetails: this.movies[this.addMovieObject()]
    }

    console.log('Save Details =' , this.showDetails)
    this.dialogRef.close(this.showDetails)
  }

  isValidSubmission() :boolean {
    if(this.movieName.pristine && this.dateTime.pristine && this.bookingAvailable.pristine) return false;
    else if(this.movieName.value == '' ) return false
    return true;

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
