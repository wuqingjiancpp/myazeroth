package com.accendl.azeroth.service.impl;

import com.accendl.azeroth.httpclient.HttpCompletableClient;
import com.accendl.azeroth.service.AzAccountService;
import com.accendl.azeroth.snoop.HttpSnoopClient;
import com.accendl.azeroth.snoop.HttpSnoopClientHandler;
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

        Document document = DocumentHelper.parseText(content);
        Node resultNode = document.selectSingleNode("//SOAP-ENV:Envelope/SOAP-ENV:Body/ns1:executeCommandResponse/result");
        if (resultNode != null){
            //Account created: test3
            String result = resultNode.getText();
            logger.error(result);
            if (StringUtils.hasText(result) && result.contains("Account created: "+username)){
                return true;
            }
        }else{
            Node faultNode = document.selectSingleNode("//SOAP-ENV:Envelope/SOAP-ENV:Body/SOAP-ENV:Fault");
            String faultCode = faultNode.getDocument().selectSingleNode("//faultcode").getText();
            String faultString = faultNode.getDocument().selectSingleNode("//faultstring").getText();
            String detail = faultNode.getDocument().selectSingleNode("//detail").getText();

            logger.error(faultCode);
            logger.error(faultString);
            //Account with this name already exist!
            logger.error(detail);

            if (StringUtils.hasText(detail) && detail.contains("Account with this name already exist!")){
                return false;
            }
        }

        return false;
    }


}
