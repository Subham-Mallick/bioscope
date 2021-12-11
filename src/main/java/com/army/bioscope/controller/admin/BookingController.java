package com.army.bioscope.controller.admin;

import com.army.bioscope.model.Booking;
import com.army.bioscope.repository.BookingRepository;
import com.army.bioscope.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 04/12/21
 */

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking newBooking){
        try {
            if (bookingService.findByBookedUserArmyNumber(newBooking.getBookedUserArmyNumber()) != null) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            final Booking booking = bookingService.save(new Booking(newBooking.getBookedUserName(), newBooking.getBookedUserArmyNumber(), newBooking.getBookedSeats()));
            return new ResponseEntity<>(booking,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable String bookingId){
        Optional<Booking> foundBooking = bookingService.findById(bookingId);

        return foundBooking.map(booking -> new ResponseEntity<>(booking, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings(@RequestParam(required = false) String bookedUserArmyNumber){
        try{
            List<Booking> bookings = new ArrayList<>();
            if(bookedUserArmyNumber==null){
                bookings.addAll(bookingService.findAll());
            } else {
                bookings.addAll(bookingService.findByBookedUserArmyNumberContaining(bookedUserArmyNumber));
            }

            if (bookings.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bookings,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bookings/{bookingId}")
    public ResponseEntity<Booking> updateBooking(@PathVariable String bookingId, @RequestBody Booking booking){
        Optional<Booking> foundBooking = bookingService.findById(bookingId);

        if(foundBooking.isPresent()){
            Booking newBooking = foundBooking.get();
            newBooking.setBookedSeats(booking.getBookedSeats());
            newBooking.setBookedUserName(booking.getBookedUserName());
            newBooking.setBookedUserArmyNumber(booking.getBookedUserArmyNumber());
            return new ResponseEntity<>(bookingService.save(newBooking),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable String bookingId){
        try{
            bookingService.deleteById(bookingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
