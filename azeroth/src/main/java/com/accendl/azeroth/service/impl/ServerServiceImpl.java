package com.accendl.azeroth.service.impl;

import com.accendl.azeroth.http.snoop.HttpSnoopClient;
import com.accendl.azeroth.service.IServerService;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Slf4j
@Service
@DubboService(version = "1.0.0", protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 6000)
public class ServerServiceImpl implements IServerService {


    private final HttpSnoopClient httpSnoopClient;

    public ServerServiceImpl(HttpSnoopClient httpSnoopClient) {
        Assert.notNull(httpSnoopClient, "HttpSnoopClient must not be null!");
        this.httpSnoopClient = httpSnoopClient;
    }

    @Override
    public String info() throws Exception {
        String command = "server info";
        return httpSnoopClient.sendCommand(command);
    }

}
