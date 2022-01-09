package com.army.bioscope.controller.admin;

import com.army.bioscope.model.Booking;
import com.army.bioscope.model.Seat;
import com.army.bioscope.model.Show;
import com.army.bioscope.service.BookingService;
import com.army.bioscope.service.ShowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.army.bioscope.controller.util.Util.getBookingResponseEntity;

/**
 * @author subham.mallick
 * @date: 04/12/21
 */

@RestController("AdminBookingController")
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
public class BookingController {
    private final ShowService showService;
    private final BookingService bookingService;

    @PostMapping("/{showId}/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking newBooking, @PathVariable String showId){
        return getBookingResponseEntity(newBooking, showId, showService, bookingService);
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

    @DeleteMapping("/{showId}/bookings/{bookingId}/")
    public ResponseEntity<Booking> deleteBooking(@PathVariable String bookingId, @PathVariable String showId){
        try{
            Show show = showService.findById(showId).get();
            if(show == null) {
                log.error("Show Id Not found and Booking deletion failed", showId);
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Booking removeBooking = null;

            List<Booking> bookings = show.getBookings() == null ? new ArrayList<>() : show.getBookings();
            int i = 0;
            boolean bookingFound = false;
            for(Booking booking: bookings) {
                if(booking.getBookingId() == bookingId) {
                    List<Seat> availableSeats = show.getAvailableSeats() == null ? new ArrayList<>() : show.getAvailableSeats();
                    for (int j = 0; j < booking.getBookedSeats().size(); j++){
                        availableSeats.add(booking.getBookedSeats().get(j));
                    }
                    show.setAvailableSeats(availableSeats);
                    bookingFound = true;
                    break;
                }
                i++;
            }
            bookings.remove(i);
            if(bookingFound == false) {
                log.error("Booking Id Not found and Booking deletion failed", bookingId);
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            // check if a booking is already done for given ArmyNumber
            show.setBookings(bookings);
            showService.save(show);
            bookingService.deleteById(bookingId);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
