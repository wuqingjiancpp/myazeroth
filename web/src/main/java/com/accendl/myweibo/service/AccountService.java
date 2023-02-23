package com.accendl.myweibo.service;

import com.accendl.account.dto.UserDTO;
import com.accendl.account.service.IAccountService;
import com.accendl.myweibo.security.customuser.CustomUser;
import com.accendl.myweibo.util.Base32Utils;
//import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.codec.binary.Base32;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    @DubboReference(version = "1.0.0")
    private IAccountService iaccountService;
    private final PasswordEncoder encoder;
    private final AzerothService azerothService;
    private final RocketMQService rocketMQService;

    private final BytesEncryptor encryptor;

    public AccountService(PasswordEncoder encoder, BytesEncryptor encryptor,
                          AzerothService azerothService, RocketMQService rocketMQService) {
        this.encoder = encoder;
        this.encryptor = encryptor;
        this.azerothService = azerothService;
        this.rocketMQService = rocketMQService;
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



//    @GlobalTransactional
    public boolean signup(String email, String password) throws Exception{
        UserDTO tmp = new UserDTO();
        tmp.setUsername(email);

        String rowPassword = password;
        String encodedPassword = encoder.encode(password);
        tmp.setPassword(encodedPassword);

        // to sync your phone with the Google Authenticator secret, hand enter the value
		// in base32Key
        String base32Key = Base32Utils.generateBase32key();
        logger.info("base32Key="+base32Key);
        Base32 base32 = new Base32();
        byte[] b = base32.decode(base32Key);
        String secret = org.apache.commons.codec.binary.Hex.encodeHexString(b);
        tmp.setSecret(secret);

        UserDTO userDTO = iaccountService.createUser(tmp);
        logger.info("账户创建成功="+userDTO.getId());
        if (userDTO == null){
            throw new Exception("账户创建失败");
        }

        boolean azFlag = rocketMQService.accountCreate(email, rowPassword);
        if (!azFlag){
            throw new Exception("世界服务器错误");
        }

        rocketMQService.sendBase32Key(userDTO.getUsername(), base32Key);

        logger.info("注册成功");
        return true;
    }
}
