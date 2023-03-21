package com.accendl.azeroth.test;

import com.accendl.azeroth.http.snoop.HttpSnoopClient;
import com.accendl.azeroth.service.impl.AccountServiceImpl;
import io.netty.channel.ChannelFuture;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestHttpClient {

    @Autowired
    private HttpSnoopClient httpSnoopClient;

    @Test
    public void testServerInfo() throws Exception {
        String httpContent = httpSnoopClient.sendCommand("server info");
        System.out.println(httpContent);
    }

}
