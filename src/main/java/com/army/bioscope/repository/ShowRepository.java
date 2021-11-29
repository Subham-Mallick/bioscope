package com.army.bioscope.repository;

import com.army.bioscope.model.Show;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author subham.mallick
 * @date: 29/11/21
 */
@Repository
public interface ShowRepository extends MongoRepository<Show,String> {
    List<Show> findAllByShowDateTime(LocalDateTime showDateTime);
}
