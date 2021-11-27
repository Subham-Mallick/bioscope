package com.army.bioscope.admin.movie.service.impl;

import com.army.bioscope.admin.movie.model.Movie;
import com.army.bioscope.admin.movie.repository.MovieRepository;
import com.army.bioscope.admin.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 20/11/21
 */

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    @Override
    public Movie saveMovie(Movie movie){
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(String id){
        return movieRepository.findById(id).get();
    }

    @Override
    public Movie updateMovie(Movie movie){
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovieById(String id){
        movieRepository.deleteById(id);
    }




















}
