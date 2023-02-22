package com.accendl.azeroth.test;

import com.accendl.azeroth.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class TestAccountService {

    @Autowired
    private AccountServiceImpl accountService;
    @Test
    public void testCreate() throws Exception {
        Assert.isTrue(accountService.create("test4", "test"), "Az账户创建失败");
    }
}
