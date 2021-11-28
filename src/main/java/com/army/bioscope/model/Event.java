package com.army.bioscope.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@Data
@Document
public class Event implements Serializable {

    @Id
    private String eventId;
    private Show showDetails;
    private List<User> bookedUsers;
    @Indexed
    private LocalDateTime eventDateTime;

    public Event(Show showDetails, List<User> bookedUsers) {
        this.showDetails = showDetails;
        this.bookedUsers = bookedUsers;
        this.eventDateTime = showDetails.getShowDateTime();
    }
}
