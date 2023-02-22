package com.accendl.azeroth.service.impl;

import com.accendl.azeroth.service.AzAccountService;
import com.accendl.azeroth.snoop.HttpSnoopClient;
import com.accendl.azeroth.snoop.HttpSnoopClientHandler;
import io.netty.channel.ChannelFuture;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

@Component
@DubboService(version = "1.0.0")
public class AccountServiceImpl implements AzAccountService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final HttpSnoopClient httpSnoopClient;

    private final HttpSnoopClientHandler httpSnoopClientHandler;


    public AccountServiceImpl(HttpSnoopClient httpSnoopClient, HttpSnoopClientHandler httpSnoopClientHandler) {
        Assert.notNull(httpSnoopClient, "HttpSnoopClient must not be null!");
        this.httpSnoopClient = httpSnoopClient;
        Assert.notNull(httpSnoopClientHandler, "HttpSnoopClientHandler must not be null!");
        this.httpSnoopClientHandler = httpSnoopClientHandler;
    }

    @Override
    public boolean create(String username, String password) throws Exception {
        String commandTemplate = ".account create $account $password";
        String command = commandTemplate.replace("$account", username)
                .replace("$password", password);

        ChannelFuture future = httpSnoopClient.post(command);

        // Now we are sure the future is completed.
        assert future.isDone();

        if (future.isCancelled()) {
            // Connection attempt cancelled by user
            logger.error("Connection attempt cancelled by user");
            return false;
        } else if (!future.isSuccess()) {
            future.cause().printStackTrace();
            return false;
        } else {
            // Connection established successfully
            logger.info("Connection established successfully");
//            String msg = httpContent.content().toString(CharsetUtil.UTF_8);
//            logger.info("message="+msg);
//            if (StringUtils.hasText(msg)){
//                if (msg.contains("Account created: ")){
//                    logger.info(msg);
//                    return true;
//                } else if (msg.contains("Account with this name already exist!")){
//                    logger.info(msg);
//                    return false;
//                }else {
//                    logger.error(msg);
//                    return false;
//                }
//            }else{
//                logger.error("世界服务器错误");
//                return false;
//            }
        }
        return false;
    }


}
