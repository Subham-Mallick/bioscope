package com.army.bioscope.service;

import com.army.bioscope.model.Auditorium;
import com.army.bioscope.repository.AuditoriumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 29/11/21
 */
@Service
@RequiredArgsConstructor
public class AuditoriumService {
    private final AuditoriumRepository auditoriumRepository;

    public Auditorium findByAudiName(String audiName){
        return auditoriumRepository.findByAudiName(audiName);
    }
    public List<Auditorium> findByAudiNameContaining(String audiName){
        return auditoriumRepository.findByAudiNameContaining(audiName);
    }

    public Auditorium save(Auditorium auditorium) {
        return auditoriumRepository.save(auditorium);
    }

    public Optional<Auditorium> findById(String audiId) {
        return auditoriumRepository.findById(audiId);
    }

    public List<Auditorium> findAll(){
        return auditoriumRepository.findAll();
    }

    public void deleteById(String audiId){
        auditoriumRepository.deleteById(audiId);
    }
}
