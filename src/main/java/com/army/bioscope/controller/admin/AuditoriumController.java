package com.army.bioscope.controller.admin;

import com.army.bioscope.model.Auditorium;
import com.army.bioscope.service.AuditoriumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author subham.mallick
 * @date: 28/11/21
 */
@RestController("AdminAuditoriumController")
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AuditoriumController {

    private final AuditoriumService auditoriumService;

    @PostMapping("/audis/new")
    public ResponseEntity<Auditorium> createAuditorium(@RequestBody Auditorium newAuditorium){
        try {
            if (auditoriumService.findByAudiName(newAuditorium.getAudiName()) != null) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
            final Auditorium audi = auditoriumService.save(
                    Auditorium.builder()
                    .audiName(newAuditorium.getAudiName())
                    .permanentSeats(newAuditorium.getPermanentSeats())
                    .build()
            );

            return new ResponseEntity<>(audi,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/audis/{audiId}")
    public ResponseEntity<Auditorium> getAuditoriumById(@PathVariable String audiId){
        Optional<Auditorium> foundAuditorium = auditoriumService.findById(audiId);
        return foundAuditorium.map(auditorium -> new ResponseEntity<>(auditorium, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/audis")
    public ResponseEntity<List<Auditorium>> getAllAuditoriums(@RequestParam(required = false) String audiName){
        try{
            List<Auditorium> auditoriums = new ArrayList<>();
            if(audiName==null){
                auditoriums.addAll(auditoriumService.findAll());
            } else {
                auditoriums.addAll(auditoriumService.findByAudiNameContaining(audiName));
            }

            if (auditoriums.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(auditoriums,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/audis/{audiId}")
    public ResponseEntity<Auditorium> updateAuditorium(@PathVariable String audiId, @RequestBody Auditorium auditorium){
        Optional<Auditorium> foundAuditorium = auditoriumService.findById(audiId);

        if(foundAuditorium.isPresent()){
            Auditorium newAuditorium = foundAuditorium.get();
            newAuditorium.setAudiName(auditorium.getAudiName());
            newAuditorium.setPermanentSeats(auditorium.getPermanentSeats());
            return new ResponseEntity<>(auditoriumService.save(newAuditorium),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/audis/{audiId}")
    public ResponseEntity<Auditorium> deleteAuditorium(@PathVariable String audiId){
        try{
            auditoriumService.deleteById(audiId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
