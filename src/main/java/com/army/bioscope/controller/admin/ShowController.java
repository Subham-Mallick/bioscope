package com.army.bioscope.controller.admin;

import com.army.bioscope.model.Auditorium;
import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Show;
import com.army.bioscope.service.AuditoriumService;
import com.army.bioscope.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.army.bioscope.controller.util.Util.getShowsResponseEntity;

/**
 * @author subham.mallick
 * @date: 04/12/21
 */

@RestController("AdminShowController")
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin
public class ShowController {
    private final ShowService showService;
    private final AuditoriumService auditoriumService;

    @PostMapping("/{audId}/shows")
    public ResponseEntity<Show> createShow(@RequestBody Show newShow, @PathVariable String audId){
        try {
            // if a show is present in same Audi with same date time, don't create it
            final Auditorium byAudiId = auditoriumService.findByAudiId(audId);
            final List<Show> shows = byAudiId.getShows();
            for (Show value : shows) {
                if (value.getShowDateTime() == newShow.getShowDateTime()) {
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                }
            }

            // Add new show details to auditorium
            Show show = new Show(newShow.getShowDateTime(), newShow.getMovieDetails(), byAudiId.getPermanentSeats());
            byAudiId.getShows().add(show);

            // save to repositories
            showService.save(show);
            auditoriumService.save(byAudiId);

            return new ResponseEntity<>(show,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/shows/{showId}")
    public ResponseEntity<Show> getShowById(@PathVariable String showId){
        Optional<Show> foundShow = showService.findById(showId);

        return foundShow.map(show -> new ResponseEntity<>(show, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/shows")
    public ResponseEntity<List<Show>> getAllShows(@RequestParam(required = false) Movie movieDetails){
        return getShowsResponseEntity(movieDetails, showService);
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
