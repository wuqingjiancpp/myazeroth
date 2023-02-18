package com.accendl.account.service;

import com.accendl.account.dto.CustomUser;

public interface IAccountService {
    CustomUser findCustomUserByEmail(String email) throws Exception;
}
