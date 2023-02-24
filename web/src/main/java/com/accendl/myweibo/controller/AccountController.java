package com.accendl.myweibo.controller;

import com.accendl.myweibo.security.customuser.CurrentUser;
import com.accendl.myweibo.security.customuser.CustomUser;
import com.accendl.myweibo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("resetPassword")
    public String resetPassword(@CurrentUser CustomUser currentUser){
        logger.info("resetPassword");
        accountService.resetPassword(currentUser);
        return "account/updatePassword";
    }

    @GetMapping("updatePasswordPage")
    public String updatePasswordPage(){
        return "account/updatePassword";
    }

    @PostMapping("updatePassword")
    public String updatePassword(@CurrentUser CustomUser currentUser, @RequestParam String password){
        logger.info("updatePassword");
        accountService.updatePassword(currentUser, password);
        return "";
    }
}
