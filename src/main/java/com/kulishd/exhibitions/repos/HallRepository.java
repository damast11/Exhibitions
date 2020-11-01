package com.kulishd.exhibitions.repos;


import com.kulishd.exhibitions.domain.Exposition;
import com.kulishd.exhibitions.domain.Hall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedQuery;
import java.util.List;
import java.util.Map;

public interface HallRepository extends CrudRepository<Hall, Long> {
    
	List<Hall> findByName(String name);
	List<Hall> findAll();

	Hall findById(Integer id);

}

