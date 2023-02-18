package com.accendl.myweibo.service;

import com.accendl.account.dto.UserDTO;
import com.accendl.account.service.IAccountService;
import com.accendl.myweibo.customuser.CustomUser;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @DubboReference(version = "1.0.0")
    private IAccountService iaccountService;


    public CustomUser findCustomUserByEmail(String username) throws Exception {
        UserDTO userDTO = iaccountService.findCustomUserByEmail(username);
        return new CustomUser(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(),
                userDTO.getSecret(), userDTO.getAnswer());
    }
}
