package com.kulishd.exhibitions.service;

import com.kulishd.exhibitions.domain.Exposition;
import com.kulishd.exhibitions.repos.ExpositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpositionService{

    @Autowired
    private ExpositionRepo expositionRepo;

    public Iterable<Exposition> findByTheme(String date ){
        return expositionRepo.findByTheme(date);
    }
    public Iterable<Exposition> findByPrice(Double price ){
        return expositionRepo.findByPrice(price);
    }
    public Iterable<Exposition> findByDate(Date date ){
        return expositionRepo.findByDate(date);
    }

    public Iterable<Exposition> findAll( ){
        return expositionRepo.findAll();
    }

    public Exposition save(Exposition exposition){
        return expositionRepo.save(exposition);
    }


    public Page<Exposition> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return expositionRepo.findAll(pageable);
    }
    public Exposition findById(Integer id){
        return expositionRepo.findExpositionById(id);
    }

}
