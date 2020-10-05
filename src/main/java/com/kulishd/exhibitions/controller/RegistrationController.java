package com.kulishd.exhibitions.controller;

import com.kulishd.exhibitions.domain.Role;
import com.kulishd.exhibitions.domain.User;
import com.kulishd.exhibitions.repos.UserRepo;
import com.kulishd.exhibitions.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        log.info("registration OK");
        return "registration";
    }
@Transactional
    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userService.findUserByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("user", "User exists!");
            log.info("User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userService.saveUser(user);
        log.info("new User registrated");
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            log.info("logout ok");
        }

        return "redirect:/login";
    }

}
