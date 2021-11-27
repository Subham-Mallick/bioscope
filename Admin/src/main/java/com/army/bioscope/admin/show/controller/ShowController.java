package com.army.bioscope.admin.show.controller;

import com.army.bioscope.admin.show.model.Show;
import com.army.bioscope.admin.show.repository.ShowRepository;
import com.army.bioscope.admin.show.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 27/11/21
 */
@RestController
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping("/all")
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.status(HttpStatus.OK).body(showService.getAllShows());
    }

    @PostMapping("/new-show")
    public ResponseEntity<Show> saveShow(@RequestBody Show show) {
        return ResponseEntity.status(HttpStatus.OK).body(showService.saveShow(show));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(showService.getShowById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateShow(@PathVariable String id, @RequestBody Show show) {
        final Show existingShow = showService.getShowById(id);
        if(existingShow == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID - "+id+" not found");
        }

        existingShow.setBookingAvailable(show.getBookingAvailable());
        existingShow.setMovie(show.getMovie());
        existingShow.setMovieDateTime(show.getMovieDateTime());
        existingShow.setId(show.getId());

        return ResponseEntity.status(HttpStatus.OK).body(showService.updateShow(existingShow));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteShowById(@PathVariable String id){
        final Show existingShow = showService.getShowById(id);
        if(existingShow == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID - "+id+" not found");
        }
        showService.deleteShowById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
