package com.accendl.rocketmq.service;

import com.accendl.account.dto.UserDTO;

public interface IAccountService {

    boolean sendBase32Key(UserDTO userDTO) throws Exception;
}
