package com.accendl.myweibo.service;

import com.accendl.account.dto.UserDTO;
import com.accendl.account.service.IAccountService;
import com.accendl.myweibo.security.customuser.CustomUser;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    @DubboReference(version = "1.0.0")
    private IAccountService iaccountService;

    private final BytesEncryptor encryptor;

    public AccountService(BytesEncryptor encryptor) {
        this.encryptor = encryptor;
    }


    public CustomUser findCustomUserByEmail(String username) throws Exception {
        UserDTO userDTO = iaccountService.findCustomUserByEmail(username);

        if (userDTO == null){
            logger.error("用户不存在");
            return null;
        }else {
            String hexSecret = userDTO.getSecret();

            String encrypted = new String(Hex.encode(encryptor.encrypt(hexSecret.getBytes())));
            logger.info("encrypted=" + encrypted);

            CustomUser customUser = new CustomUser(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(),
                    encrypted, userDTO.getAnswer());
            logger.info(customUser.toString());

            return customUser;
        }
    }
}
