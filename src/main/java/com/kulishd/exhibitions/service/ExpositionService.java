package com.kulishd.exhibitions.service;

import com.kulishd.exhibitions.domain.Exposition;
import com.kulishd.exhibitions.repos.ExpositionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpositionService {

    @Autowired
    private ExpositionRepo expositionRepo;

    public Page<Exposition> findByPagination(LocalDate date, int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return expositionRepo.findByDate(date, pageable);
    }

    public List<Exposition> findByPrice(Double price) {
        return expositionRepo.findByPrice(price);
    }

    public List<Exposition> findByDate(LocalDate date) {
        return expositionRepo.findByDate(date);
    }

    public List<Exposition> findAll() {
        return expositionRepo.findAll();
    }

    public Exposition save(Exposition exposition) {
        return expositionRepo.save(exposition);
    }


    public Page<Exposition> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return expositionRepo.findAll(pageable);
    }


    public Page<Exposition> findAllByDate(LocalDate date, int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return expositionRepo.findByDate(date, pageable);
    }



    public Exposition findById(Integer id) {
        return expositionRepo.findExpositionById(id);
    }

    @Transactional
    public void deleteExposition(Integer id) {
        expositionRepo.deleteExpositionById(id);
    }

    @Transactional
    public void updateTicket(Integer id) {
        expositionRepo.updateCountOfTickets(id);
    }
}
