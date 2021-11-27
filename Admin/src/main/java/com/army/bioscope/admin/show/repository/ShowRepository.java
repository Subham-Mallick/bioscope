package com.army.bioscope.admin.show.repository;

import com.army.bioscope.admin.show.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author subham.mallick
 * @date: 27/11/21
 */
@Repository
public interface ShowRepository extends JpaRepository<Show,String> {
}
