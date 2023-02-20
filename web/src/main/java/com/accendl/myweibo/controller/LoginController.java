package com.accendl.myweibo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutConfirm() {
        return "logout";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

}