package com.army.bioscope.repository;

import com.army.bioscope.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 04/12/21
 */
public interface BookingRepository extends MongoRepository<Booking,String> {

    Booking findByBookedUserArmyNumber(String bookedUserArmyNumber);
    List<Booking> findByBookedUserArmyNumberContaining(String bookedUserArmyNumber);


}
