package com.army.bioscope.admin.movie.service;

import com.army.bioscope.admin.movie.model.Movie;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 20/11/21
 */
public interface MovieService {

    List<Movie> getAllMovies();
    Movie saveMovie(Movie movie);
    Movie getMovieById(String id);
    Movie updateMovie(Movie movie);
    void deleteMovieById(String id);

}
