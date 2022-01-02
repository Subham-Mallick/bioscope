package com.army.bioscope.controller.client;

import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Show;
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
 * @date: 12/12/21
 */
@RestController("ClientShowController")
@RequestMapping("/client")
@RequiredArgsConstructor
@CrossOrigin
public class ShowController {

    private final ShowService showService;

    @GetMapping("/shows")
    public ResponseEntity<List<Show>> getAllShows(@RequestParam(required = false) Movie movieDetails){
        return getShowsResponseEntity(movieDetails, showService);
    }

    @GetMapping("/shows/{showId}")
    public ResponseEntity<Show> getShowById(@PathVariable String showId){
        Optional<Show> foundShow = showService.findById(showId);

        return foundShow.map(show -> new ResponseEntity<>(show, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
