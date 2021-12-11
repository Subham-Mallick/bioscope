package com.army.bioscope.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Data
@Document
@Builder
public class Movie implements Serializable {

    @Id
    private String movieId;
    private String movieName;
    private String movieDescription;
}
