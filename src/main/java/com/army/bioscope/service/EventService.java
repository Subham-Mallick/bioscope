package com.army.bioscope.service;

import com.army.bioscope.model.Event;
import com.army.bioscope.repository.EventRepository;
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
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> findAllByEventDateTime(LocalDateTime eventDateTime){
        return eventRepository.findAllByEventDateTime(eventDateTime);
    }
}
