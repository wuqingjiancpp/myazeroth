package com.accendl.myweibo.test;

import com.accendl.myweibo.service.AccountService;
import com.accendl.myweibo.service.AzerothService;
import com.accendl.myweibo.service.RocketMQService;
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
