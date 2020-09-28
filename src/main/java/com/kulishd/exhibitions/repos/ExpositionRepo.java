package com.kulishd.exhibitions.repos;

import com.kulishd.exhibitions.domain.Exposition;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ExpositionRepo extends CrudRepository<Exposition, Long> {

    List<Exposition> findByDate(Date date);

}
