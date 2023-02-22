package com.accendl.azeroth.test;

import com.accendl.azeroth.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAccountService {

    @Autowired
    private AccountServiceImpl accountService;
    @Test
    public void testCreate() throws Exception {
        accountService.create("test", "test");
    }
}
