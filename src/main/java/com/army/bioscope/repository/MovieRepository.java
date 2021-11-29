package com.army.bioscope.repository;

import com.army.bioscope.model.Event;
import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {
    List<Movie> findAllByMovieName(String movieName);
}
