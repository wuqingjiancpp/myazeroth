package com.accendl.rocketmq.service.impl;

import com.accendl.account.dto.UserDTO;
import com.accendl.rocketmq.service.IAccountService;
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
@DubboService(version = "1.0.0", protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}", timeout = 30000)
public class AccountServiceImpl implements IAccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    private final StreamBridge streamBridge;

    public AccountServiceImpl(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    public boolean sendBase32Key(UserDTO userDTO) throws Exception{
        UserDTO payload = new UserDTO(userDTO.getBase32Key(), userDTO.getUsername());
        MessageBuilder<UserDTO> builder = MessageBuilder.withPayload(payload);
        builder.setHeader("username", userDTO.getUsername())
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader(RocketMQConst.USER_TRANSACTIONAL_ARGS, "binder")
                .setHeader(RocketMQConst.PROPERTY_MAX_RECONSUME_TIMES, 3);
        Message<UserDTO> msg = builder.build();
        try {
            boolean flag = streamBridge.send("producer-out-1", msg);
            if (flag){
                logger.info("send Msg success:" + msg);
                return true;
            }else{
                logger.info("send Msg fail:" + msg);
                return false;
            }
        }catch (Exception e){
            throw new Exception("发送base32key失败");
        }
    }

}
