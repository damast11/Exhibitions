package com.kulishd.exhibitions.controller;

import com.kulishd.exhibitions.domain.Exposition;
import com.kulishd.exhibitions.domain.User;
import com.kulishd.exhibitions.repos.ExpositionRepo;
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
    private ExpositionRepo expositionRepo;

//    @GetMapping("/")
//    public String greeting(Map<String, Object> model) {
//        return "greeting";
//    }

    @GetMapping("/")
    public String main(@RequestParam(required = false) Date filter, Model model) {
        Iterable<Exposition> expositions = expositionRepo.findAll();

        if (filter != null ) {
            expositions = expositionRepo.findByDate(filter);
        } else {
            expositions = expositionRepo.findAll();
        }

        model.addAttribute("expositions", expositions);
        model.addAttribute("filter", filter);

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

        expositionRepo.save(exposition);

        Iterable<Exposition> expositions = expositionRepo.findAll();

        model.put("expositions", expositions);

        return "main";
    }
}
