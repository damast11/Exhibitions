package com.kulishd.exhibitions.repos;


import com.kulishd.exhibitions.domain.Hall;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HallRepository extends CrudRepository<Hall, Long> {
    
	List<Hall> findByName(String name);
	List<Hall> findAll();

	Hall findById(Integer id);
}

