package com.accendl.azeroth.service.impl;

import com.accendl.azeroth.http.snoop.HttpSnoopClient;
import com.accendl.azeroth.service.AzAccountService;
import com.accendl.azeroth.util.DomUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Service
@DubboService(version = "1.0.0", protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 6000)
public class AccountServiceImpl implements AzAccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final HttpSnoopClient httpSnoopClient;

    public AccountServiceImpl(HttpSnoopClient httpSnoopClient) {
        Assert.notNull(httpSnoopClient, "HttpSnoopClient must not be null!");
        this.httpSnoopClient = httpSnoopClient;
    }

    @Override
    public boolean create(String username, String password) throws Exception {
        String commandTemplate = ".account create $account $password";
        String command = commandTemplate.replace("$account", username)
                .replace("$password", password);
        logger.info("send command="+command);
        String content = httpSnoopClient.sendCommand(command);
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
        String commandTemplate = ".account set password $account $password $password";
        String command = commandTemplate.replace("$account", username)
                                .replace("$password", password);
        logger.info("send command="+command);
        String content = httpSnoopClient.sendCommand(command);
        logger.info("content="+content);

        String parsedContent = DomUtils.parseHttpContent(content);
        if(parsedContent.contains("The password was changed")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean accountSetAddon(String username) throws Exception {
        String commandTemplate = ".account set addon [$account] #addon";
        String command = commandTemplate.replace("[$account]", username)
                            .replace("#addon", 2+"");
        logger.info("send command="+command);
        String content = httpSnoopClient.sendCommand(command);
        logger.info("content="+content);
        String parsedContent = DomUtils.parseHttpContent(content);
        if(parsedContent.contains("Account not exist: "+username.toUpperCase())){
            return false;
        }else{
            return true;
        }
    }


}
