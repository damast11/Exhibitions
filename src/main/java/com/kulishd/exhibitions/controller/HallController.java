package com.kulishd.exhibitions.controller;

import com.kulishd.exhibitions.domain.Exposition;
import com.kulishd.exhibitions.domain.Hall;
import com.kulishd.exhibitions.service.ExpositionService;
import com.kulishd.exhibitions.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HallController {

    @Autowired
    private HallService hallService;
    @Autowired
    private ExpositionService expositionService;

    @GetMapping("/addExpositionHall/{id}")
    public String addHall(@PathVariable("id") Integer expositionId, Model model){
        model.addAttribute("halls", hallService.findAllHalls());
        model.addAttribute("exposition", expositionService.findById(expositionId));
        return "add_exposition_hall";
    }



    @GetMapping("/exposition/{id}/halls")
    public String expositionsAddHall(@PathVariable Integer id, @RequestParam Integer hallId, Model model) {

        Hall hall = hallService.findHallById(hallId);
        Exposition exposition = expositionService.findById(id);

        if (exposition != null) {
            if (!exposition.hasHall(hall)) {
                exposition.getHalls().add(hall);
            }
            expositionService.save(exposition);
            model.addAttribute("exposition", expositionService.findById(id));
            model.addAttribute("halls", hallService.findAllHalls());
            return "redirect:/";
        }

        return "redirect:/";
    }
}
