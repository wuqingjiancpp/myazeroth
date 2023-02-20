package com.accendl.myweibo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(){
        if (true){
            logger.info("注册成功");
            return "redirect:/signup?success";
        }else {
            logger.info("注册失败");
            return "redirect:/signup?error";
        }
    }

}