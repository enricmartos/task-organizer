package org.emartos.controllers;

import org.emartos.entities.User;
import org.emartos.services.TaskService;
import org.emartos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("profile")
public class ProfileController {

    private static final String INDEX_PROFILE_PAGE = "views/profile/index";

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    //This functionality is only available when the user is logged in
    //we can know it using Principal class (spring security
    @GetMapping("")
    public String showProfile(Model model, Principal principal) {

        String email = principal.getName();

        User user = userService.findByEmail(email);

        model.addAttribute("tasks", taskService.findByUser(user));
        return INDEX_PROFILE_PAGE;
    }
}
