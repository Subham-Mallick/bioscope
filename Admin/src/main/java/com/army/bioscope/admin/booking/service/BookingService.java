package com.army.bioscope.admin.booking.service;

import com.army.bioscope.admin.booking.model.Booking;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 27/11/21
 */
public interface BookingService {
    List<Booking> getAllBookings();
    Booking saveBooking(Booking booking);
    Booking getBookingById(String id);
    Booking updateBooking(Booking booking);
    void deleteBookingById(String id);
}
