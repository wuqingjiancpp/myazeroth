package com.accendl.myweibo.service;

import com.accendl.account.dto.CustomUser;
import com.accendl.account.service.IAccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @DubboReference(version = "1.0.0")
    private IAccountService iaccountService;


    public CustomUser findCustomUserByEmail(String username) throws Exception {
        return iaccountService.findCustomUserByEmail(username);
    }
}
