package com.accendl.rocketmq.service.impl;

import com.accendl.account.dto.UserDTO;
import com.accendl.azeroth.dto.AzAccountDTO;
import com.accendl.azeroth.service.AzAccountService;
import com.alibaba.cloud.stream.binder.rocketmq.constant.RocketMQConst;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@DubboService(version = "1.0.0")
public class AzAccountServiceImpl implements AzAccountService {

    private static final Logger logger = LoggerFactory.getLogger(AzAccountServiceImpl.class);

    private final StreamBridge streamBridge;

    public AzAccountServiceImpl(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public boolean create(String username, String password) {
        AzAccountDTO azAccountDTO = new AzAccountDTO(username, password);
        MessageBuilder builder = MessageBuilder.withPayload(azAccountDTO);
        builder.setHeader("username", azAccountDTO.getUsername())
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader(RocketMQConst.USER_TRANSACTIONAL_ARGS, "binder");
        Message<UserDTO> msg = builder.build();
        logger.info("send Msg:" + msg.toString());
        return streamBridge.send("producer-out-0", msg);
    }
}
