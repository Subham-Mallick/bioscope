package com.army.bioscope.admin.booking.repository;

import com.army.bioscope.admin.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author subham.mallick
 * @date: 27/11/21
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking,String> {
}
