package com.army.bioscope.service;

import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Show;
import com.army.bioscope.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 29/11/21
 */
@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;

    public Show findByMovieDetails(Movie movie){
        return showRepository.findByMovieDetails(movie);
    }

    public List<Show> findByMovieDetailsContaining(Movie movieDetails){
        return showRepository.findByMovieDetailsContaining( movieDetails);
    }

    public List<Show> findAllByShowDateTime(LocalDateTime showDateTime){
        return showRepository.findAllByShowDateTime(showDateTime);
    }

    public Show save(Show show) {
        return showRepository.save(show);
    }

    public Optional<Show> findById(String audiId) {
        return showRepository.findById(audiId);
    }

    public List<Show> findAll(){
        return showRepository.findAll();
    }

    public void deleteById(String audiId){
        showRepository.deleteById(audiId);
    }


}
