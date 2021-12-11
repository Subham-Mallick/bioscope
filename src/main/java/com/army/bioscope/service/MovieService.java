package com.army.bioscope.service;

import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Movie;
import com.army.bioscope.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<Movie> findByMovieName(String movieName){
        return movieRepository.findByMovieName(movieName);
    }
    public List<Movie> findByMovieNameContaining(String movieName){
        return movieRepository.findByMovieNameContaining(movieName);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public Optional<Movie> findById(String audiId) {
        return movieRepository.findById(audiId);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public void deleteById(String audiId){
        movieRepository.deleteById(audiId);
    }

}
