package com.army.bioscope.repository;

import com.army.bioscope.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {
    List<Movie> findByMovieName(String movieName);
    List<Movie> findByMovieNameContaining(String movieName);
}
