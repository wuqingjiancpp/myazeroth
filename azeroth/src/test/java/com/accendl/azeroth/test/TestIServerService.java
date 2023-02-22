package com.accendl.azeroth.test;

import com.accendl.azeroth.service.impl.ServerServiceImpl;
import org.apache.dubbo.common.utils.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
public class TestIServerService {

    @Autowired
    private ServerServiceImpl serverService;

    @Test
    public void testChannelFuture() throws Exception {
        Assert.notEmptyString(serverService.info(), "can't get server info");
    }
}
