package com.army.bioscope.admin.movie.repository;

import com.army.bioscope.admin.movie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author subham.mallick
 * @date: 20/11/21
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie,String> {
}
