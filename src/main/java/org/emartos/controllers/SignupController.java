package org.emartos.controllers;

import org.emartos.entities.User;
import org.emartos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("signup")
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "views/signup/index";
    }

    @PostMapping("")
    //Valid annotation to apply validation to the user set in the entity
    //Binding Result->bind exceptions to the view is the validation is not successful
    public String signupUser(@Valid User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "views/signup/index";
        }
        if(userService.isUserPresent(user.getEmail())) {
            model.addAttribute("exist", true);
            return "views/signup/index";
        }
        userService.createUser(user);
        return "views/signup/success";


    }


}
