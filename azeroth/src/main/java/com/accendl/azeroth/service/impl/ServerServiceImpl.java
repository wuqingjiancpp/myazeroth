package com.accendl.azeroth.service.impl;

import com.accendl.azeroth.service.HttpContentFuture;
import com.accendl.azeroth.service.IServerService;
import com.accendl.azeroth.snoop.HttpSnoopClient;
import com.accendl.azeroth.snoop.HttpSnoopClientHandler;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.accendl.azeroth.snoop.HttpSnoopClientHandler.httpContentFuture;

@Service
@DubboService(version = "1.0.0", protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 30000)
public class ServerServiceImpl implements IServerService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final HttpSnoopClient httpSnoopClient;
    private final HttpSnoopClientHandler httpSnoopClientHandler;

    public ServerServiceImpl(HttpSnoopClient httpSnoopClient, HttpSnoopClientHandler httpSnoopClientHandler) {
        Assert.notNull(httpSnoopClient, "HttpSnoopClient must not be null!");
        this.httpSnoopClient = httpSnoopClient;
        Assert.notNull(httpSnoopClientHandler, "HttpSnoopClientHandler must not be null!");
        this.httpSnoopClientHandler = httpSnoopClientHandler;
    }

    @Override
    public String info() throws Exception {
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
            return message;
        }
        return "";
    }

}
