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

    @Autowired
    private UserService userService;

    private List<String> roles = new ArrayList<>();



    @GetMapping("")
    public String showSignupForm(Model model) {
        roles.add("USER");
        roles.add("ADMIN");
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        return "views/signup/index";
    }

    @PostMapping("")
    //Valid annotation to apply validation to the user set in the entity
    //Binding Result->bind exceptions to the view is the validation is not successful
//    public String processSignupForm(@Valid User user, @RequestParam String roleName, BindingResult bindingResult, Model model) {
    public String processSignupForm(@ModelAttribute @Valid User user, Errors errors, Model model, @RequestParam String roleName) {
        if(errors.hasErrors()) {
            roles.add("USER");
            roles.add("ADMIN");
            model.addAttribute("roles", roles);
            return "views/signup/index";
        }
        if(userService.isUserPresent(user.getEmail())) {
            model.addAttribute("exist", true);
            return "views/signup/index";
        }
        user.setRole(roleName);
        userService.saveOne(user);
        return "views/signup/success";
    }

}
