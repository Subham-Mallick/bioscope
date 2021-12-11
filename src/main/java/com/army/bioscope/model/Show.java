package com.army.bioscope.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Data
@Document
public class Show implements Serializable {

    @Id
    private String showId;
    private List<Booking> bookings;
    private List<Seat> availableSeats;
    private LocalDateTime showDateTime;
    private Boolean bookingAvailable;
    private Movie movieDetails;

    public Show(LocalDateTime showDateTime, Movie movieDetails, List<Seat> availableSeats) {
        this.showDateTime = showDateTime;
        this.bookingAvailable = true;
        this.movieDetails = movieDetails;
        this.availableSeats = availableSeats;
    }
}
