package com.accendl.azeroth.service.impl;

import com.accendl.azeroth.httpclient.HttpCompletableClient;
import com.accendl.azeroth.service.AzAccountService;
import com.accendl.azeroth.snoop.HttpSnoopClient;
import com.accendl.azeroth.snoop.HttpSnoopClientHandler;
import com.accendl.azeroth.util.DomUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Service
@DubboService(version = "1.0.0", protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 30000)
public class AccountServiceImpl implements AzAccountService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final HttpSnoopClient httpSnoopClient;

    private final HttpSnoopClientHandler httpSnoopClientHandler;

    private final HttpCompletableClient httpCompletableClient;


    public AccountServiceImpl(HttpSnoopClient httpSnoopClient, HttpSnoopClientHandler httpSnoopClientHandler,
                              HttpCompletableClient httpCompletableClient) {
        Assert.notNull(httpCompletableClient, "HttpCompletableClient must not be null!");
        this.httpCompletableClient = httpCompletableClient;
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

        String content = httpCompletableClient.sendCommand(command);
        logger.info("content="+content);

        String parsedContent = DomUtils.parseHttpContent(content);
        if (StringUtils.hasText(parsedContent) && parsedContent.contains("Account created: "+username)){
            return true;
        } else if (StringUtils.hasText(parsedContent) && parsedContent.contains("Account with this name already exist!")) {
            return false;
        }else {
            return false;
        }
    }

    @Override
    public boolean resetPassword(String username, String password) throws Exception {
        return false;
    }

    @Override
    public boolean updatePassword(String username, String oldPassword, String newPassword) throws Exception {
        return false;
    }


}
