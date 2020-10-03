package com.kulishd.exhibitions.repos;

import com.kulishd.exhibitions.domain.Exposition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public interface ExpositionRepo extends JpaRepository<Exposition, Long> {
    List<Exposition> findByTheme(String theme);
    List<Exposition> findByPrice(Double price);
    List<Exposition> findByDate(LocalDate date);
    List<Exposition> findAll();
    Exposition findExpositionById(Integer id);
    void deleteExpositionById(Integer id);

    Page<Exposition> findByDate(LocalDate date, Pageable aInPageable);





}
