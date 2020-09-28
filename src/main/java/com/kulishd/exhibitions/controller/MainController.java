package com.kulishd.exhibitions.controller;

import com.kulishd.exhibitions.domain.Exposition;
import com.kulishd.exhibitions.domain.User;
import com.kulishd.exhibitions.repos.ExpositionRepo;
import com.kulishd.exhibitions.service.ExpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ExpositionService expositionService;


    @GetMapping("/")
    public String main(@RequestParam(required = false, defaultValue = "") String filterTheme,
                       @RequestParam(required = false, defaultValue = "") Double filterPrice,
                       @RequestParam(required = false) Date filterDate, Model model) {
        Iterable<Exposition> expositions;

        if (filterTheme != null && !filterTheme.isEmpty()) {
            expositions = expositionService.findByTheme(filterTheme);
            model.addAttribute("filterTheme", filterTheme);
        }
        else if(filterPrice!=null ){
            expositions = expositionService.findByPrice(filterPrice);
            model.addAttribute("filterPrice", filterPrice);
        }
        else if (filterDate!=null){
            expositions=expositionService.findByDate(filterDate);
            model.addAttribute("filterDate", filterDate);
        }
        else {
            expositions = expositionService.findAll();
        }
        model.addAttribute("expositions", expositions);

        return "main";
    }

    @PostMapping("/")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String theme,
            @RequestParam Double price,
            @RequestParam Date date, Map<String, Object> model
    ) {
        Exposition exposition = new Exposition(theme, price, date, user);

        expositionService.save(exposition);

        Iterable<Exposition> expositions = expositionService.findAll();

        model.put("expositions", expositions);

        return "main";
    }

}
