package com.kulishd.exhibitions.controller;

import com.kulishd.exhibitions.domain.Role;
import com.kulishd.exhibitions.domain.User;
import com.kulishd.exhibitions.repos.UserRepo;
import com.kulishd.exhibitions.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model) {
       model.addAttribute("users", userService.findAllUsers());
        log.info("add all users");
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        log.info("go to user");
        return "userEdit";
    }
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userService.saveUser(user);

        return "redirect:/user";
    }
}
