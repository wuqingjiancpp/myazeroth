package com.accendl.account.service;

import com.accendl.account.dto.UserDTO;

public interface IEmailService {

    boolean sendBase32Key(UserDTO userDTO) throws Exception;
}
