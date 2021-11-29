package com.army.bioscope;

import com.army.bioscope.model.Movie;
import com.army.bioscope.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class BioscopeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BioscopeApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(MovieRepository movieRepository, MongoTemplate mongoTemplate){

        return args -> {
            Movie movie = new Movie("MovieName1","FirstMovie");

            System.out.println("Inserting movie " + movie.getMovieName());
            movieRepository.insert(movie);

        };

    }

}
