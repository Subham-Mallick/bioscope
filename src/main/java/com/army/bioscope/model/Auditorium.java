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
public class Auditorium implements Serializable {
    @Id
    private String auditoriumId;
    private String auditoriumName;
}
