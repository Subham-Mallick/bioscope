package com.army.bioscope.controller.admin;

import com.army.bioscope.model.Event;
import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Show;
import com.army.bioscope.service.EventService;
import com.army.bioscope.service.MovieService;
import com.army.bioscope.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@RestController
@RequestMapping("bioscope/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EventService eventService;
    private final ShowService showService;
    private final MovieService movieService;

    @GetMapping("/event/{eventDateTime}")
    public List<Event> findAllByEventDateTime(@PathVariable LocalDateTime eventDateTime){
        return eventService.findAllByEventDateTime(eventDateTime);
    }

    @GetMapping("/show/{showDateTime}")
    public List<Show> findAllByShowDateTime(@PathVariable LocalDateTime showDateTime){
        return showService.findAllByShowDateTime(showDateTime);
    }

    @GetMapping("/movie/{movieName}")
    public List<Movie> findAllByMovieName(@PathVariable String movieName){
        return movieService.findAllByMovieName(movieName);
    }
}
