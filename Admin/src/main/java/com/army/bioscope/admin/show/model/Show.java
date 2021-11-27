package com.army.bioscope.admin.show.model;

import com.army.bioscope.admin.movie.model.Movie;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author subham.mallick
 * @date: 20/11/21
 */
@Getter
@Setter
@ToString
public class Show implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private LocalDateTime movieDateTime;
    private Movie movie;
    private Boolean bookingAvailable;
}
