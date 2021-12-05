package com.army.bioscope.controller.admin;

import com.army.bioscope.model.Movie;
import com.army.bioscope.repository.MovieRepository;
import com.army.bioscope.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 04/12/21
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies/new")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie newMovie){
        try {
            if (movieService.findByMovieName(newMovie.getMovieName()) != null) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            final Movie movie = movieService.save(
                    Movie.builder()
                            .movieName(newMovie.getMovieName())
                            .movieDescription(newMovie.getMovieDescription())
                            .build()
            );
            return new ResponseEntity<>(movie,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String movieId){
        Optional<Movie> foundMovie = movieService.findById(movieId);

        return foundMovie.map(movie -> new ResponseEntity<>(movie, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies(@RequestParam(required = false) String movieName){
        try{
            List<Movie> movies = new ArrayList<>();
            if(movieName==null){
                movies.addAll(movieService.findAll());
            } else {
                movies.addAll(movieService.findByMovieNameContaining(movieName));
            }

            if (movies.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(movies,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String movieId, @RequestBody Movie movie){
        Optional<Movie> foundMovie = movieService.findById(movieId);

        if(foundMovie.isPresent()){
            Movie newMovie = foundMovie.get();
            newMovie.setMovieName(movie.getMovieName());
            newMovie.setMovieDescription(movie.getMovieDescription());
            return new ResponseEntity<>(movieService.save(newMovie),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable String movieId){
        try{
            movieService.deleteById(movieId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
