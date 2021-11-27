package com.army.bioscope.admin.show.model;

import com.army.bioscope.admin.movie.model.Movie;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
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
@Entity
public class Show implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;


    private Movie movie;




}