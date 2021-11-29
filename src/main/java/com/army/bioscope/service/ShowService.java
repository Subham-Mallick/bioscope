package com.army.bioscope.service;

import com.army.bioscope.model.Show;
import com.army.bioscope.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author subham.mallick
 * @date: 29/11/21
 */
@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;

    public List<Show> findAllByShowDateTime(LocalDateTime showDateTime){
        return showRepository.findAllByShowDateTime(showDateTime);
    }

}
