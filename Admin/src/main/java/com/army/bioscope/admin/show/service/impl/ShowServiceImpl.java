package com.army.bioscope.admin.show.service.impl;

import com.army.bioscope.admin.movie.service.MovieService;
import com.army.bioscope.admin.show.model.Show;
import com.army.bioscope.admin.show.repository.ShowRepository;
import com.army.bioscope.admin.show.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 27/11/21
 */

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;

    @Override
    public List<Show> getAllShows(){
        return showRepository.findAll();
    }
    @Override
    public Show saveShow(Show show){
        return showRepository.save(show);
    }
    @Override
    public Show getShowById(String id){
        return showRepository.findById(id).get();
    }
    @Override
    public Show updateShow(Show show){
        return showRepository.save(show);
    }
    @Override
    public void deleteShowById(String id){
        showRepository.deleteById(id);
    }

}
