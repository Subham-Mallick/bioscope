package com.army.bioscope.controller.client;

import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Show;
import com.army.bioscope.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.army.bioscope.controller.util.Util.getShowsResponseEntity;

/**
 * @author subham.mallick
 * @date: 12/12/21
 */
@RestController("ClientShowController")
@RequestMapping("/client")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping("/shows")
    public ResponseEntity<List<Show>> getAllShows(@RequestParam(required = false) Movie movieDetails){
        return getShowsResponseEntity(movieDetails, showService);
    }

}
