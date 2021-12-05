package com.army.bioscope.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Data
@Document
@Builder
public class Auditorium implements Serializable {
    @Id
    private String audiId;
    @Indexed(unique = true)
    private String audiName;
    private List<Seat> permanentSeats;
    private List<Show> shows;
}
