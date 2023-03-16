package com.accendl.account.dubbo.impl;

import com.accendl.account.dto.UserDTO;
import com.accendl.account.dubbo.IAccountService;
import com.accendl.account.service.impl.UserServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Service
@DubboService(version = "1.0.0", protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 6000)
public class AccountServiceImpl implements IAccountService {

    private final UserServiceImpl userService;

    public AccountServiceImpl(UserServiceImpl usersService) {
        this.userService = usersService;
    }

    @Override
    public UserDTO findCustomUserByEmail(String email) throws Exception {
        return userService.findCustomUserByEmail(email);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) throws Exception {
        return userService.createUser(userDTO);
    }

    @Override
    public boolean updateUserPassword(String username, String password) throws Exception {
        return userService.updatePassword(username, password);
    }
}
