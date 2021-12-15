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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@SpringBootApplication
@RequiredArgsConstructor
public class BioscopeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BioscopeApplication.class, args);
    }

}
