package org.emartos.controllers;

import org.emartos.entities.User;
import org.emartos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("signup")
public class SignupController {

    private static final String INDEX_SIGNUP_PAGE = "views/signup/index";
    private static final String SUCCESS_SIGNUP_PAGE = "views/signup/success";

    @Autowired
    private UserService userService;

    private List<String> roles = new ArrayList<>();



    @GetMapping("")
    public String showSignupForm(Model model) {
        //Clear the list first in order to avoid duplicates in every GET request
        roles.clear();
        roles.add("USER");
        roles.add("ADMIN");
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        return INDEX_SIGNUP_PAGE;
    }

    @PostMapping("")
    //Valid annotation to apply validation to the user set in the entity
    //Binding Result->bind exceptions to the view is the validation is not successful
//    public String processSignupForm(@Valid User user, @RequestParam String roleName, BindingResult bindingResult, Model model) {
    public String processSignupForm(@ModelAttribute @Valid User user, Errors errors, Model model, @RequestParam String roleName) {
        if(errors.hasErrors()) {
//            roles.add("USER");
//            roles.add("ADMIN");
            model.addAttribute("roles", roles);
            return INDEX_SIGNUP_PAGE;
        }
        else if(userService.isUserPresent(user.getEmail())) {
//            roles.add("USER");
//            roles.add("ADMIN");
            model.addAttribute("roles", roles);
            model.addAttribute("exist", true);
            return INDEX_SIGNUP_PAGE;
        }
        user.setRole(roleName);
        userService.saveOne(user);
        return SUCCESS_SIGNUP_PAGE ;
    }

}
