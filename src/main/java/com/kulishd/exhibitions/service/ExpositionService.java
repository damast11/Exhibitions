package com.kulishd.exhibitions.service;

import com.kulishd.exhibitions.domain.Exposition;
import com.kulishd.exhibitions.repos.ExpositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

}
