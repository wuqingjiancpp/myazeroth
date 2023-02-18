package com.accendl.azeroth.service.impl;

import com.accendl.azeroth.service.ServerService;
import com.accendl.azeroth.snoop.HttpSnoopClient;
import com.accendl.azeroth.snoop.HttpSnoopClientHandler;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.concurrent.CompletableFuture;

@Component
@DubboService(version = "1.0.0")
public class ServerServiceImpl implements ServerService {

    private final HttpSnoopClient httpSnoopClient;

    public ServerServiceImpl(HttpSnoopClient httpSnoopClient) {
        Assert.notNull(httpSnoopClient, "HttpSnoopClient must not be null!");
        this.httpSnoopClient = httpSnoopClient;
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
        } else if (!future.isSuccess()) {
            future.cause().printStackTrace();
        } else {
            // Connection established successfully
            HttpContent httpContent = HttpSnoopClientHandler.httpContent;
            return httpContent.content().toString(CharsetUtil.UTF_8);
        }

        return "";
    }

}
