package com.mia.BuhaiCommunications.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String loginPage(Model model) {
        return "security/login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String registerPage(Model model) {
        return "security/register";
    }
}
