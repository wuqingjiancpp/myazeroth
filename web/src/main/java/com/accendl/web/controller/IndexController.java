package com.accendl.web.controller;

import com.accendl.web.security.customuser.CustomUser;
import com.accendl.web.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
@Slf4j
public class IndexController {

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
        log.info(email);
        CustomUser customUser = accountService.findCustomUserByEmail(email);
        if (customUser == null){
            return "ok";
        }else{
            return "duplicate";
        }
    }



}
