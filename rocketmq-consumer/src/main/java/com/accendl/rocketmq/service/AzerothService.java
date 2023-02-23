package com.accendl.rocketmq.service;

import com.accendl.azeroth.dto.AzAccountDTO;
import com.accendl.azeroth.service.AzAccountService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class AzerothService {

    private static final Logger logger = LoggerFactory.getLogger(AzerothService.class);

    @DubboReference(version = "1.0.0")
    private AzAccountService accountService;

    @Bean
    public Consumer<Message<AzAccountDTO>> createAccount() {
        return msg -> {
            Object arg = msg.getHeaders();
            logger.info(Thread.currentThread().getName() + " Receive New Messages: " +
                    msg.getPayload() + " ARG:"+ arg);
            AzAccountDTO azAccountDTO = msg.getPayload();
            try {
                boolean flag = accountService.create(azAccountDTO.getUsername(), azAccountDTO.getPassword());
                if (flag){
                    logger.info("创建账户成功: "+azAccountDTO.getUsername());
                }else{
                    logger.info("创建账户失败: "+azAccountDTO.getUsername());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
