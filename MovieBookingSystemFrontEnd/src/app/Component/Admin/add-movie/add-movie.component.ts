import { Component, OnInit, SkipSelf } from '@angular/core';
import { MovieEditAddComponent } from './dialog/movie-edit-add/movie-edit-add.component'

import { MatDialog } from '@angular/material/dialog';
import { MovieService } from './services/movie.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-movie',
  templateUrl: './add-movie.component.html',
  styleUrls: ['./add-movie.component.scss']
})
export class AddMovieComponent implements OnInit {

  movies: any = []
  constructor(public dialog: MatDialog, private movieService: MovieService, private _snackBar: MatSnackBar) {

   }

  ngOnInit(): void {
    this.movies = [];
    this.getAllMovies();
  }

  openDialog(mode: string, movieDetails: any, index: number): void {
    
    const dialogRef = this.dialog.open(MovieEditAddComponent, {
      data: {
        mode: mode,
        movieDetails
      },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      if(result != undefined) {
          
          if(mode == 'Add') this.addMovie(result)
          else this.updateMovie(result, index);
      }
      
    });
  }

  addMovie(movie: any) {
    this.movieService.addNewmovie(movie).subscribe(response => {
      console.log(response);
      this.movies.push(response);
      this.openSnackBar("New Movie Created", "Success")
    }, (error) => {
      this.openSnackBar("Unable to create the movie", "Failed");
    })
  }

  updateMovie(movie: any, index: number) {
    console.log("Movie Updating")
    this.movieService.updatemovie(movie).subscribe(response => { 
      this.movies[index] = response
      this.openSnackBar("Movie Updated", "Success")
    }, (error) => {
      this.openSnackBar("Failed to Update the Movie", "Failed");
    })
  }


  deleteMovie(movie: any) { 
    console.log(movie);
    this.movieService.deletemovie(movie)
                      .subscribe(response => {
                        this.getAllMovies();
                        this.openSnackBar("Deletion Successful" , "Success")
                      }, (errors)=> {
                        this.openSnackBar("Unable to Delete Please Try again" , "Failed")
                      });
    
  }

  getAllMovies() {
    this.movieService.getAllmovies().subscribe((response: any) => {
      this.movies = response 
      
      console.log(this.movies)
    }, (errors) => {
      console.log(errors)
      
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action, { duration: 3000 });
  }

}
