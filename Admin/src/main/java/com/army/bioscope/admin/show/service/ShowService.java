package com.army.bioscope.admin.show.service;

import com.army.bioscope.admin.show.model.Show;

import java.util.List;

/**
 * @author subham.mallick
 * @date: 27/11/21
 */
public interface ShowService {

    List<Show> getAllShows();
    Show saveShow(Show show);
    Show getShowById(String id);
    Show updateShow(Show show);
    void deleteShowById(String id);
}
