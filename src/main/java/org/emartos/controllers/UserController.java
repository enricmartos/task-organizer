package org.emartos.controllers;

import org.emartos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("user")
public class UserController {

    private static final String INDEX_USER_PAGE = "views/user/index";

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showUsers(Model model, @RequestParam(defaultValue="")  String name) {
        model.addAttribute("users", userService.findByName(name));
        return INDEX_USER_PAGE;
    }
}
