package com.accendl.account.test;

import com.accendl.account.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class TestAccountService {

    @Autowired
    private AccountServiceImpl accountService;

    @Test
    public void testCheckUser() throws Exception{
        String username = "";
    }
}
