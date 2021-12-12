package com.army.bioscope.controller.admin;

import com.army.bioscope.model.Booking;
import com.army.bioscope.model.Seat;
import com.army.bioscope.model.Show;
import com.army.bioscope.repository.BookingRepository;
import com.army.bioscope.service.BookingService;
import com.army.bioscope.service.ShowService;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author subham.mallick
 * @date: 04/12/21
 */

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class BookingController {
    private final ShowService showService;
    private final BookingService bookingService;

    @PostMapping("/{showId}/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking newBooking, @PathVariable String showId){
        try {
            final Show show = showService.findById(showId).get();

            // if booking is not open for this show
            if(!show.getBookingAvailable()){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // check if a booking is already done for given ArmyNumber
            final List<Booking> bookings = show.getBookings();
            for(Booking booking: bookings){
                if(Objects.equals(booking.getBookedUserArmyNumber(), newBooking.getBookedUserArmyNumber())){
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                }
            }

            // check if seats requested for booking is available with in show
            HashSet<Pair<Integer,Integer>> availableSeats = new HashSet<>();
            for(Seat availableSeat : show.getAvailableSeats()){
                availableSeats.add(new Pair<>(availableSeat.getRow(),availableSeat.getColumn()));
            }

            for(Seat bookedSeat : newBooking.getBookedSeats()){
                // if a seat requested is not available in show, don't book
                if(!availableSeats.contains(new Pair<>(bookedSeat.getRow(),bookedSeat.getColumn()))){
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                }
            }

            // Save the booking
            final Booking booking = bookingService.save(
                    new Booking(newBooking.getBookedUserName(), newBooking.getBookedUserArmyNumber(), newBooking.getBookedSeats())
            );

            // Remove booked seats from show
            for(Seat bookedSeat : newBooking.getBookedSeats()){
                show.getAvailableSeats().removeIf(
                        availableSeat -> bookedSeat.getRow() == availableSeat.getRow() && bookedSeat.getColumn() == availableSeat.getColumn()
                );
            }

            // save the show
            showService.save(show);
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
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
