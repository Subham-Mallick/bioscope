package com.army.bioscope.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @author subham.mallick
 * @date: 04/12/21
 */
@Data
@Document
public class Booking implements Serializable {
    @Id
    private String bookingId;
    private String bookedUserName;
    private String bookedUserArmyNumber;
    private List<Seat> bookedSeats;

    public Booking(String bookedUserName, String bookedUserArmyNumber, List<Seat> bookedSeats) {
        this.bookedUserName = bookedUserName;
        this.bookedUserArmyNumber = bookedUserArmyNumber;
        this.bookedSeats = bookedSeats;
    }
}
