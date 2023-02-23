package com.accendl.myweibo.controller;

import com.accendl.myweibo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AccountService accountService;

    public LoginController(AccountService accountService) {
        this.accountService = accountService;
    }

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
    public String signup(@RequestParam("email") String email,
                         @RequestParam("password") String password) throws Exception{
        boolean flag = accountService.signup(email, password);
        if (flag){
            logger.info("注册成功");
            return "redirect:/signup?success";
        }else {
            logger.info("注册失败");
            return "redirect:/signup?error";
        }
    }

}