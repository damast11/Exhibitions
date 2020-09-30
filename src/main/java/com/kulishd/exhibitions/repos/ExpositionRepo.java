package com.kulishd.exhibitions.repos;

import com.kulishd.exhibitions.domain.Exposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.Date;
import java.util.List;

public interface ExpositionRepo extends JpaRepository<Exposition, Long> {
    List<Exposition> findByTheme(String theme);
    List<Exposition> findByPrice(Double price);
    List<Exposition> findByDate(Date date);
    List<Exposition> findAll();
    Exposition findExpositionById(Integer id);


}
