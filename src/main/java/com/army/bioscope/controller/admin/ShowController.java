package com.army.bioscope.controller.admin;

import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Show;
import com.army.bioscope.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 04/12/21
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;

    @PostMapping("/shows")
    public ResponseEntity<Show> createShow(@RequestBody Show show){
        try {
            if (showService.findByMovieDetails(show.getMovieDetails()) != null) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(showService.save(new Show(show.getShowDateTime(), show.getMovieDetails(),null)),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/shows/{showId}")
    public ResponseEntity<Show> getShowById(@PathVariable String showId){
        Optional<Show> foundShow = showService.findById(showId);

        return foundShow.map(show -> new ResponseEntity<>(show, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/shows")
    public ResponseEntity<List<Show>> getAllShows(@RequestParam(required = false) Movie movieDetails){
        try{
            List<Show> shows = new ArrayList<>();
            if(movieDetails==null){
                shows.addAll(showService.findAll());
            } else {
                shows.addAll(showService.findByMovieDetailsContaining(movieDetails));
            }

            if (shows.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(shows,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/shows/{showId}")
    public ResponseEntity<Show> updateShow(@PathVariable String showId, @RequestBody Show show){
        Optional<Show> foundShow = showService.findById(showId);

        if(foundShow.isPresent()){
            Show newShow = foundShow.get();
            newShow.setShowDateTime(show.getShowDateTime());
            newShow.setBookingAvailable(show.getBookingAvailable());
            newShow.setMovieDetails(show.getMovieDetails());
            newShow.setBookings(show.getBookings());
            newShow.setAvailableSeats(show.getAvailableSeats());

            return new ResponseEntity<>(showService.save(newShow),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/shows/{showId}")
    public ResponseEntity<Show> deleteShow(@PathVariable String showId){
        try{
            showService.deleteById(showId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
