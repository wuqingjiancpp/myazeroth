package com.accendl.rocketmq.test;

import com.accendl.rocketmq.service.impl.AzerothServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAzerothService {

    @Autowired
    private AzerothServiceImpl accountService;
    @Test
    public void testAccountCreate() throws Exception {
        accountService.accountCreate("test", "test");
    }
}
