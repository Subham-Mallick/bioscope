package com.army.bioscope.controller.admin;

import com.army.bioscope.model.Event;
import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Show;
import com.army.bioscope.service.BioscopeService;
import lombok.Getter;
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

    private final BioscopeService bioscopeService;

    @GetMapping("/event/{eventDateTime}")
    public List<Event> findEventsByEventDateTime(@PathVariable LocalDateTime eventDateTime){
        return bioscopeService.findEventsByEventDateTime(eventDateTime);
    }

    @GetMapping("/show/{showDateTime}")
    public List<Show> findShowsByShowDateTime(@PathVariable LocalDateTime showDateTime){
        return bioscopeService.findShowsByShowDateTime(showDateTime);
    }

    @GetMapping("/movie/{movieName}")
    public List<Movie> findAllByMovieName(@PathVariable String movieName){
        return bioscopeService.findAllByMovieName(movieName);
    }
}
