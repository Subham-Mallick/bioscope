package com.army.bioscope.controller.util;

import com.army.bioscope.model.Booking;
import com.army.bioscope.model.Movie;
import com.army.bioscope.model.Seat;
import com.army.bioscope.model.Show;
import com.army.bioscope.service.BookingService;
import com.army.bioscope.service.ShowService;
import javafx.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * @author subham.mallick
 * @date: 12/12/21
 */
public class Util {

    public static ResponseEntity<List<Show>> getShowsResponseEntity(@RequestParam(required = false) Movie movieDetails, ShowService showService) {
        try{
            List<Show> shows = new ArrayList<>();
            if(movieDetails==null){
                shows.addAll(showService.findAll());
            } else {
                shows.addAll(showService.findByMovieDetailsContaining(movieDetails));
            }

            if (shows.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(shows,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static ResponseEntity<Booking> getBookingResponseEntity(@RequestBody Booking newBooking, @PathVariable String showId, ShowService showService, BookingService bookingService) {
        try {
            final Show show = showService.findById(showId).get();

            // if booking is not open for this show
            if(!show.getBookingAvailable()){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // check if a booking is already done for given ArmyNumber
            final List<Booking> bookings = show.getBookings() == null ? new ArrayList<>() : show.getBookings();
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

            bookings.add(booking);
            show.setBookings(bookings);
            // save the show
            showService.save(show);
            return new ResponseEntity<>(booking,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
