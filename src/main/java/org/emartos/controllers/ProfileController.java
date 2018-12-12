package org.emartos.controllers;

import org.emartos.entities.User;
import org.emartos.services.TaskService;
import org.emartos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    //This functionality is only available when the user is logged in
    //we can know it using Principal class (spring security
    @GetMapping("/profile")
    public String showProfilePage(Model model, Principal principal) {

        String email = principal.getName();

        User user = userService.findByEmail(email);

        model.addAttribute("tasks", taskService.findUserTask(user));
        return "views/profile";
    }
}
