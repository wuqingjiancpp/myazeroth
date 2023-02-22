package com.accendl.rocketmq.service.impl;

import com.accendl.account.dto.UserDTO;
import com.accendl.account.service.IEmailService;
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
public class EmailServiceImpl implements IEmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final StreamBridge streamBridge;

    public EmailServiceImpl(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public boolean sendBase32Key(UserDTO userDTO){
        MessageBuilder builder = MessageBuilder.withPayload(userDTO);
        builder.setHeader("userId", userDTO.getId())
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader(RocketMQConst.USER_TRANSACTIONAL_ARGS, "binder");
        Message<UserDTO> msg = builder.build();
        logger.info("send Msg:" + msg.toString());
        return streamBridge.send("producer-out-0", msg);
    }

}
