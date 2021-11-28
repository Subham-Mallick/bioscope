package com.army.bioscope.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Data
@Document
public class Show implements Serializable {

    @Id
    private String showId;
    @Indexed
    private LocalDateTime showDateTime;
    private Boolean bookingAvailable;
    private Movie movieDetails;

    public Show(LocalDateTime showDateTime, Boolean bookingAvailable, Movie movieDetails) {
        this.showDateTime = showDateTime;
        this.bookingAvailable = bookingAvailable;
        this.movieDetails = movieDetails;
    }
}
