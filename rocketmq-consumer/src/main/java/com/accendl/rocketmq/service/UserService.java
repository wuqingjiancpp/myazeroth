package com.accendl.rocketmq.service;

import com.accendl.account.dto.UserDTO;
import com.accendl.azeroth.service.AzAccountService;
import com.accendl.mail.service.IUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @DubboReference(version = "1.0.0")
    private IUserService userService;

    @Bean
    public Consumer<Message<UserDTO>> sendBase32Key() {
        return msg -> {
            Object arg = msg.getHeaders();
            logger.info(Thread.currentThread().getName() + " Receive New Messages: " +
                    msg.getPayload() + " ARG:"+ arg);
            UserDTO userDTO = msg.getPayload();
            try {
                userService.sendQROfGoogleAuthenticator(userDTO.getUsername(), userDTO.getBase32Key());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        };
    }
}
