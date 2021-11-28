package com.army.bioscope.service;

import com.army.bioscope.model.Event;
import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Show;
import com.army.bioscope.repository.BioscopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Service
@RequiredArgsConstructor
public class BioscopeService {
    private final BioscopeRepository bioscopeRepository;

    public List<Event> findEventsByEventDateTime(LocalDateTime eventDateTime){
        return bioscopeRepository.findEventsByEventDateTime(eventDateTime);
    }

    public List<Show> findShowsByShowDateTime(LocalDateTime showDateTime) {
        return bioscopeRepository.findShowsByShowDateTime(showDateTime);
    }

    public List<Movie> findAllByMovieName(String movieName) {
        return bioscopeRepository.findAllByMovieName(movieName);
    }
}
