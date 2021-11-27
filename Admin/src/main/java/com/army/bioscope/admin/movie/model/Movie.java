package com.army.bioscope.admin.movie.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author subham.mallick
 * @date: 20/11/21
 */
@Getter
@Setter
@ToString
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String movieName;
    private String movieDescription;

}
