package com.army.bioscope.admin.booking.controller;

import com.army.bioscope.admin.booking.model.Booking;
import com.army.bioscope.admin.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 27/11/21
 */
@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getAllBookings());
    }

    @PostMapping("/new-booking")
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.saveBooking(booking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.getBookingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBooking(@PathVariable String id, @RequestBody Booking booking) {
        final Booking existingBooking = bookingService.getBookingById(id);
        if(existingBooking == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID - "+id+" not found");
        }
        existingBooking.setId(booking.getId());
        existingBooking.setSeats(booking.getSeats());
        existingBooking.setShow(booking.getShow());
        existingBooking.setUser(booking.getUser());

        return ResponseEntity.status(HttpStatus.OK).body(bookingService.updateBooking(existingBooking));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBookingById(@PathVariable String id){
        final Booking existingBooking = bookingService.getBookingById(id);
        if(existingBooking == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID - "+id+" not found");
        }
        bookingService.deleteBookingById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
