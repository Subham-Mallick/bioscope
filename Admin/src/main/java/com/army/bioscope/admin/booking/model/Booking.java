package com.army.bioscope.admin.booking.model;

import com.army.bioscope.admin.show.model.Show;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * @author subham.mallick
 * @date: 20/11/21
 */
@Getter
@Setter
@ToString
public class Booking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Show show;
    private User user;
    private List<String> seats;
}
