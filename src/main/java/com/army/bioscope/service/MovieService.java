package com.army.bioscope.service;

import com.army.bioscope.model.Movie;
import com.army.bioscope.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

//    public List<Event> findEventsByEventDateTime(LocalDateTime eventDateTime){
//        return movieRepository.findEventsByEventDateTime(eventDateTime);
//    }

//    public List<Show> findShowsByShowDateTime(LocalDateTime showDateTime) {
//        return movieRepository.findShowsByShowDateTime(showDateTime);
//    }
//
    public List<Movie> findAllByMovieName(String movieName) {
        return movieRepository.findAllByMovieName(movieName);
    }
}
