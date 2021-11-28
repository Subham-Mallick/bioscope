package com.army.bioscope.model;

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
public class User implements Serializable {

    @Id
    private String userId;
    @Indexed(unique = true)
    private String userName;
    private String userArmyNumber;
    private List<Seat> seatsBooked;

    public User(String userName, String userArmyNumber, List<Seat> seatsBooked) {
        this.userName = userName;
        this.userArmyNumber = userArmyNumber;
        this.seatsBooked = seatsBooked;
    }
}
