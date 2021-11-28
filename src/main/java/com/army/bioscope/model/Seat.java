package com.army.bioscope.model;

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
public class Seat implements Serializable {
    @Id
    private String seatId;
    private int row;
    private int column;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
