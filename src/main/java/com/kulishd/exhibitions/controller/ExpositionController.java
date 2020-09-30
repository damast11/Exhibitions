package com.kulishd.exhibitions.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.kulishd.exhibitions.domain.Exposition;
import com.kulishd.exhibitions.domain.User;
import com.kulishd.exhibitions.service.ExpositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ExpositionController {

    @Autowired
    private ExpositionService expositionService;

    //display list of expositions
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "theme", "asc", model);
    }

//    @GetMapping("/")
//    public String main(@RequestParam(required = false, defaultValue = "") String filterTheme,
//                       @RequestParam(required = false, defaultValue = "") Double filterPrice,
//                       @RequestParam(required = false) Date filterDate, Model model) {
//        Iterable<Exposition> expositions;
//
//        if (filterTheme != null && !filterTheme.isEmpty()) {
//            expositions = expositionService.findByTheme(filterTheme);
//            model.addAttribute("filterTheme", filterTheme);
//        }
//        else if(filterPrice!=null ){
//            expositions = expositionService.findByPrice(filterPrice);
//            model.addAttribute("filterPrice", filterPrice);
//        }
//        else if (filterDate!=null){
//            expositions=expositionService.findByDate(filterDate);
//            model.addAttribute("filterDate", filterDate);
//        }
//        else {
//            expositions = expositionService.findAll();
//        }
//        model.addAttribute("expositions", expositions);
//
//        return "main";
//    }

    @GetMapping("/showNewExpositionForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Exposition exposition = new Exposition();
        model.addAttribute("exposition", exposition);
        return "new_exhibition";
    }

    @PostMapping("/saveExposition")
    public String saveExposition(
            @AuthenticationPrincipal User user,
            @RequestParam String theme,
            @RequestParam Double price,
            @RequestParam Date date, Map<String, Object> model
    ) {
        Exposition exposition = new Exposition(theme, price, date, user);

        expositionService.save(exposition);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Exposition> page = expositionService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Exposition> listExpositions = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listExpositions", listExpositions);
        return "main";
    }


    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {

        // get employee from the service
        Exposition exposition = expositionService.findById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("exposition", exposition);
        return "update_exhibition";
    }

//    @GetMapping("/deleteEmployee/{id}")
//    public String deleteEmployee(@PathVariable(value = "id") long id) {
//
//        // call delete employee method
//        this.expositionService.deleteEmployeeById(id);
//        return "redirect:/";
//    }
}


