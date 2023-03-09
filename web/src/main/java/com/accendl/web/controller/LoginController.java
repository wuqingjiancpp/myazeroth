package com.accendl.web.controller;

import com.accendl.web.dto.ServerInfo;
import com.accendl.web.service.AccountService;
import com.accendl.web.service.AzerothService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AccountService accountService;
    private final AzerothService azerothService;

    public LoginController(AccountService accountService, AzerothService azerothService) {
        this.accountService = accountService;
        Assert.notNull(azerothService, "AzerothService must not be null!");
        this.azerothService = azerothService;
    }

    @GetMapping("/login")
    public String login() {
        return "account/signin";
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

    @ModelAttribute
    public ServerInfo getServerInfo() throws Exception{
        ServerInfo serverInfo = azerothService.getServerInfo();
        return serverInfo;
    }

}