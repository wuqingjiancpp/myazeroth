package com.accendl.myweibo.test;

import com.accendl.web.service.AccountService;
import com.accendl.web.service.AzerothService;
import com.accendl.web.service.RocketMQService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestRocketMQ {

    @Autowired
    private AzerothService azerothService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RocketMQService rocketMQService;

    @Test
    public void testAzAccountCreate() throws Exception {
        rocketMQService.accountCreate("test", "test");
    }

    @Test
    public void testSendBase32Key() throws Exception{
        rocketMQService.sendBase32Key("test", "base32key");
    }
}
