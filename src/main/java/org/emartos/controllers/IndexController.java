package org.emartos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private static final String INDEX_PAGE = "index";

    private static final String INDEX_LOGIN_PAGE = "views/login/index";

    @GetMapping("/")
    public String showIndex() {
        return INDEX_PAGE;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return INDEX_LOGIN_PAGE;
    }

}
