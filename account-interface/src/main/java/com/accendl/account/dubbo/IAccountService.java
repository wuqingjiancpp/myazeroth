package com.accendl.account.dubbo;

import com.accendl.account.dto.UserDTO;

public interface IAccountService {
    UserDTO findCustomUserByEmail(String email) throws Exception;
    UserDTO createUser(UserDTO userDTO) throws Exception;

    boolean updateUserPassword(String username, String password) throws Exception;

}
