package com.accendl.account.service.impl;

import com.accendl.account.dto.CustomUser;
import com.accendl.account.service.IAccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DubboService(version = "1.0.0")
public class AccountServiceImpl implements IAccountService {

    private final UsersServiceImpl usersService;

    public AccountServiceImpl(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @Override
    public CustomUser findCustomUserByEmail(String email) throws Exception {
        return usersService.findCustomUserByEmail(email);
    }
}
