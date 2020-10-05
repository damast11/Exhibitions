package com.kulishd.exhibitions.controller;

import com.kulishd.exhibitions.domain.Exposition;
import com.kulishd.exhibitions.service.ExpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class StatisticsController {

    @Autowired
    private ExpositionService expositionService;



    @GetMapping("/statistics")
    public String filter(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate filterString, Model model) {
        model.addAttribute("filterString", filterString);
        return findPaginated(filterString,1, "theme", "asc", model);
    }

    @GetMapping("/statistics/page/{pageNo}")
    public String findPaginated(@RequestParam(required = false, value = "filterString") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                @PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 5;
        Page<Exposition> page;
        if (date != null ) {
            page = expositionService.findAllByDate(date, pageNo, pageSize, sortField, sortDir);
        } else {
            page = expositionService.findPaginated(pageNo, pageSize, sortField, sortDir);
        }
        List<Exposition> listExpositions = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listExpositions", listExpositions);

        return "statisticsList";
    }






}
