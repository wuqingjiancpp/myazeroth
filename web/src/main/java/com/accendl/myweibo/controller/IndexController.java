package com.accendl.myweibo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * 校验Email是否可用
     *
     * @param email
     * @return
     */
    @GetMapping("checkEmailExists/{email}")
    public String checkEmailExists(@PathVariable String email){
        logger.info(email);
        if (true){
            return "ok";
        }else{
            return "duplicate";
        }
    }
}
