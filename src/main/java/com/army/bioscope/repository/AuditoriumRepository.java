package com.army.bioscope.repository;

import com.army.bioscope.model.Auditorium;
import com.army.bioscope.model.Seat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 04/12/21
 */
public interface AuditoriumRepository extends MongoRepository<Auditorium,String> {
    Auditorium findByAudiName(String audiName);
    List<Auditorium> findByAudiNameContaining(String audiName);
    Auditorium findByAudiId(String audiId);
}
