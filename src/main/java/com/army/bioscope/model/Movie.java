package com.army.bioscope.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Data
@Document
public class Movie implements Serializable {

    @Id
    private String movieId;
    @Indexed
    private String movieName;
    private String movieDescription;

    public Movie(String movieName, String movieDescription) {
        this.movieName = movieName;
        this.movieDescription = movieDescription;
    }
}
