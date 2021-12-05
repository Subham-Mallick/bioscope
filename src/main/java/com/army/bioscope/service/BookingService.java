package com.army.bioscope.service;

import com.army.bioscope.model.Booking;
import com.army.bioscope.model.Booking;
import com.army.bioscope.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 05/12/21
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    public Booking findByBookedUserArmyNumber(String bookedUserArmyNumber){
        return bookingRepository.findByBookedUserArmyNumber(bookedUserArmyNumber);
    }

    public List<Booking> findByBookedUserArmyNumberContaining(String bookedUserArmyNumber){
        return bookingRepository.findByBookedUserArmyNumberContaining(bookedUserArmyNumber);
    }

    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Optional<Booking> findById(String audiId) {
        return bookingRepository.findById(audiId);
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }

    public void deleteById(String audiId){
        bookingRepository.deleteById(audiId);
    }
    

}
