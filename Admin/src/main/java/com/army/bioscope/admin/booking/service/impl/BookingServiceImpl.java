package com.army.bioscope.admin.booking.service.impl;

import com.army.bioscope.admin.booking.model.Booking;
import com.army.bioscope.admin.booking.repository.BookingRepository;
import com.army.bioscope.admin.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 27/11/21
 */
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBookingById(String id) {
        bookingRepository.deleteById(id);
    }
}
