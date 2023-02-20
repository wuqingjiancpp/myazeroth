package com.accendl.myweibo.controller;

import com.accendl.myweibo.security.customuser.CustomUser;
import com.accendl.myweibo.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AccountService accountService;

    public IndexController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 校验Email是否可用
     *
     * @param email
     * @return
     */
    @GetMapping("checkEmailExists/{email}")
    public String checkEmailExists(@PathVariable String email) throws Exception {
        logger.info(email);
        CustomUser customUser = accountService.findCustomUserByEmail(email);
        if (customUser == null){
            return "ok";
        }else{
            return "duplicate";
        }
    }
}
