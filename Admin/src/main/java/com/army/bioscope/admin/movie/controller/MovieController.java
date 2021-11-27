package com.army.bioscope.admin.movie.controller;

import com.army.bioscope.admin.movie.model.Movie;
import com.army.bioscope.admin.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 20/11/21
 */
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
    }

    @PostMapping("/new-movie")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.saveMovie(movie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovieById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        final Movie existingMovie = movieService.getMovieById(id);
        if(existingMovie == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID - "+id+" not found");
        }
        existingMovie.setMovieDescription(movie.getMovieDescription());
        existingMovie.setMovieName(movie.getMovieName());
        existingMovie.setId(movie.getId());

        return ResponseEntity.status(HttpStatus.OK).body(movieService.updateMovie(existingMovie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMovieById(@PathVariable String id){
        final Movie existingMovie = movieService.getMovieById(id);
        if(existingMovie == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID - "+id+" not found");
        }
        movieService.deleteMovieById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
