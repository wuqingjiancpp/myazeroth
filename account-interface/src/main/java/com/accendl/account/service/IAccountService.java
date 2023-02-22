package com.accendl.account.service;

import com.accendl.account.dto.UserDTO;

public interface IAccountService {
    UserDTO findCustomUserByEmail(String email) throws Exception;
    UserDTO createUser(UserDTO userDTO) throws Exception;


}
