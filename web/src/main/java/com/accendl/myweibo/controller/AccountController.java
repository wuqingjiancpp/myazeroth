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
    public String resetPassword(@CurrentUser CustomUser currentUser,  @RequestParam String resetPassword,
                                @RequestParam String confirmPassword) throws Exception {
        boolean flag = accountService.resetPassword(currentUser, resetPassword, confirmPassword);
        if (flag){
            logger.info("resetPassword success");
            return "redirect:/account/updatePasswordPage?success";
        }else{
            logger.info("resetPassword fail");
            return "redirect:/account/updatePasswordPage?error";
        }
    }

    @GetMapping("updatePasswordPage")
    public String updatePasswordPage(){
        return "account/updatePassword";
    }

    @PostMapping("updatePassword")
    public String updatePassword(@CurrentUser CustomUser currentUser, @RequestParam String originalPassword,
                                 @RequestParam String newPassword, @RequestParam String repeatNewPassword) throws Exception {
        boolean flag = accountService.updatePassword(currentUser, originalPassword, newPassword, repeatNewPassword);
        if (flag){
            logger.info("updatePassword success");
            return "redirect:/account/updatePasswordPage?success";
        }else{
            logger.info("updatePassword fail");
            return "redirect:/account/updatePasswordPage?error";
        }
    }
}
