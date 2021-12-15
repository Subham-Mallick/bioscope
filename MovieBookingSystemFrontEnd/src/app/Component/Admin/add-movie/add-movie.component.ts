import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MovieEditAddComponent } from './dialog/movie-edit-add/movie-edit-add.component'

import { MatDialog } from '@angular/material/dialog';
import { MovieService } from './services/movie.service';
import { HttpResponseBase } from '@angular/common/http';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.scss']
})
export class AddMovieComponent implements OnInit {

  movies: any = []
  constructor(public dialog: MatDialog, private movieService: MovieService) {

   }

  ngOnInit(): void {
    this.movies = [];
    this.movieService.getAllmovies().subscribe((response: any) => this.movies = response );
  }

  openDialog(mode: string, movieDetails: any): void {
    
    const dialogRef = this.dialog.open(MovieEditAddComponent, {
      data: {
        mode: mode,
        movieDetails
      },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      console.log('The dialog was closed');
      if(mode == 'Add') this.movieService.addNewmovie(result).subscribe(response => {
        console.log(response);
      })
      else this.movieService.updatemovie(result).subscribe(response => { console.log(response)})
    });
  }

}
