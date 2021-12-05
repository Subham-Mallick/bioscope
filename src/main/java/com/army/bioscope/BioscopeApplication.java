package com.army.bioscope;

import com.army.bioscope.model.*;
import com.army.bioscope.repository.AuditoriumRepository;
import com.army.bioscope.repository.BookingRepository;
import com.army.bioscope.repository.MovieRepository;
import com.army.bioscope.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BioscopeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BioscopeApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AuditoriumRepository auditoriumRepository, BookingRepository bookingRepository, MovieRepository movieRepository, ShowRepository showRepository, MongoTemplate mongoTemplate){

        return args -> {
            List<Seat> seats = new ArrayList<>();
            seats.add(new Seat(1,1));
            seats.add(new Seat(2,2));
            seats.add(new Seat(3,3));

            // create audi
            Auditorium auditorium = Auditorium.builder()
                    .audiName("auditorium-name-1")
                    .permanentSeats(Arrays.asList(
                                    new Seat(1,1),
                                    new Seat(2,2),
                                    new Seat(3,3)))
                    .build();
            auditoriumRepository.save(auditorium);

            // create movies
            Movie movie_1 = Movie.builder()
                    .movieName("movie-name-1")
                    .movieDescription("1st movie added")
                    .build();

            Movie movie_2= Movie.builder()
                    .movieName("movie-name-2")
                    .movieDescription("2nd movie added")
                    .build();

            movieRepository.saveAll(Arrays.asList(movie_1,movie_2));

            // create shows
            Show show_1 = new Show(LocalDateTime.now(),movie_1,auditoriumRepository.findById(auditorium.getAudiId()).get().getPermanentSeats());
            Show show_2 = new Show(LocalDateTime.now().minusDays(19),movie_2,auditoriumRepository.findById(auditorium.getAudiId()).get().getPermanentSeats());
//            showRepository.saveAll(Arrays.asList(show_1,show_2));

            // add shows to an auditorium
            auditorium.setShows(Arrays.asList(show_1,show_2));
            auditoriumRepository.save(auditorium);


            // add booking
//            for(Show bookedShow: auditoriumRepository.findById(auditorium.getAudiId()).get().getShows()){
//                System.out.println(auditorium.getShows());
//                if(bookedShow.equals(show_2)){
//
//                    bookedShow.getAvailableSeats().remove(bookedShow.getAvailableSeats().size() - 1);
////                    auditorium.getShows().add(bookedShow);
//                }
//                if(bookedShow.equals(show_2)){
//
//                    final List<Seat> toBooks = Arrays.asList(new Seat(1,1), new Seat(2,2));
//                    final List<Seat> availableSeats = bookedShow.getAvailableSeats();
//                    if(Collections.indexOfSubList(bookedShow.getAvailableSeats(),toBooks)==-1){
//                        System.out.println("Booking not possible");
//                        System.out.println(availableSeats);
//                        System.out.println(toBooks);
//                    }
//                    for (Seat toBook:toBooks) {
//                        availableSeats.remove(toBook);
//                    }
//                    bookedShow.setAvailableSeats(availableSeats);
//
//                    Booking booking = new Booking("user-1","army-1",toBooks);
//                    bookedShow.getBookings().add(booking);
//                }
//            }

            final List<Show> shows = auditoriumRepository.findById(auditorium.getAudiId()).get().getShows();
            for(int i=0;i<shows.size();i++){
                if(shows.get(i).equals(show_2)){
                    shows.get(i).getAvailableSeats().remove(shows.get(i).getAvailableSeats().size()-1);
                }
            }
            auditorium.setShows(shows);

            // save to mongo
            auditoriumRepository.save(auditorium);

        };

    }

}
