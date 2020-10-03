package com.kulishd.exhibitions.service;

import com.kulishd.exhibitions.domain.Hall;
import com.kulishd.exhibitions.repos.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    @Autowired
    private HallRepository hallRepository;

    public List<Hall> findByHall(String name){
        return hallRepository.findByName(name);
    }

    public List<Hall> findAllHalls(){
        return hallRepository.findAll();
    }

    public Hall findHallById(Integer hallId) {
        return hallRepository.findById(hallId);
    }
}
