package com.accendl.azeroth.test;

import com.accendl.azeroth.service.HttpContentFuture;
import com.accendl.azeroth.service.impl.AccountServiceImpl;
import com.accendl.azeroth.snoop.HttpSnoopClient;
import com.accendl.azeroth.snoop.HttpSnoopClientHandler;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.FutureTask;

@SpringBootTest
public class TestHttpClient {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private HttpSnoopClient httpSnoopClient;

    @Autowired
    private HttpSnoopClientHandler httpSnoopClientHandler;

    @Test
    public void testFuture() throws Exception {
        String command = "server info";
        ChannelFuture future = httpSnoopClient.post(command);

        future.awaitUninterruptibly();

        // Now we are sure the future is completed.
        assert future.isDone();

        if (future.isCancelled()) {
            // Connection attempt cancelled by user
            logger.info("Connection attempt cancelled by user");
        } else if (!future.isSuccess()) {
            future.cause().printStackTrace();
        } else {
            // Connection established successfully
            logger.info("Connection established successfully");
            String message = (String) httpSnoopClientHandler.httpContentFuture.get();
            logger.info("message="+message);
        }

    }
}
