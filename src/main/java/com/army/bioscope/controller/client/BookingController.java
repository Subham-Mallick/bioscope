package com.army.bioscope.controller.client;

import com.army.bioscope.model.Booking;
import com.army.bioscope.service.BookingService;
import com.army.bioscope.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.army.bioscope.controller.util.Util.getBookingResponseEntity;


/**
 * @author subham.mallick
 * @date: 28/11/21
 */

@RestController("ClientBookingController")
@RequiredArgsConstructor
@RequestMapping("/client")
public class BookingController {

    private final ShowService showService;
    private final BookingService bookingService;

    @PostMapping("/{showId}/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking newBooking, @PathVariable String showId){
        return getBookingResponseEntity(newBooking, showId, showService, bookingService);
    }




}
