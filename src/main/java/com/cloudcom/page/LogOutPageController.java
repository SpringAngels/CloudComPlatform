package com.cloudcom.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("logout")
public class LogOutPageController {

    @GetMapping
    public String logout() {
        return "logout";
    }
}
