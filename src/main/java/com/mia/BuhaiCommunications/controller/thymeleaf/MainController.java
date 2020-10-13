package com.mia.BuhaiCommunications.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String showHomepage(Model model) {
        return "security/login";
    }
}
