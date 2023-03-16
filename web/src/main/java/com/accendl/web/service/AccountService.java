package com.accendl.web.service;

import com.accendl.account.dto.UserDTO;
import com.accendl.account.dubbo.IAccountService;
import com.accendl.web.security.customuser.CustomUser;
import com.accendl.web.util.Base32Utils;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.codec.binary.Base32;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

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



    @GlobalTransactional
    public boolean signup(String email, String password) throws Exception{
        // 校验输入
        if (!StringUtils.hasText(email) || !StringUtils.hasText(password)){
            logger.error("请输入email 和 密码");
            return false;
        }
        if (!Pattern.matches("([\\w\\-]+\\@[\\w\\-]+\\.[\\w\\-]+)", email)){
            logger.error("email 格式错误");
            return false;
        }

        String rowPassword = password;
        String encodedPassword = encoder.encode(password);

        // to sync your phone with the Google Authenticator secret, hand enter the value
		// in base32Key
        String base32Key = Base32Utils.generateBase32key();
        logger.info("base32Key="+base32Key);
        Base32 base32 = new Base32();
        byte[] b = base32.decode(base32Key);
        String secret = org.apache.commons.codec.binary.Hex.encodeHexString(b);

        UserDTO tmp = UserDTO.builder()
                        .username(email)
                        .password(encodedPassword)
                        .secret(secret)
                        .build();
        UserDTO userDTO = iaccountService.createUser(tmp);

        if (userDTO == null){
            throw new Exception("账户创建失败");
        }else{
            logger.info("账户创建成功="+userDTO.getId());
        }

        boolean flag = azerothService.accountSetAddon(email);
        if (flag){
            logger.error("世界账户已存在");
            throw new Exception("世界账户已存在: "+email);
        }

        boolean azFlag = rocketMQService.accountCreate(email, rowPassword);
        if (!azFlag){
            throw new Exception("世界服务器错误");
        }

        rocketMQService.sendBase32Key(email, base32Key);

        logger.info("注册成功");
        return true;
    }

    @GlobalTransactional
    public boolean resetPassword(CustomUser currentUser, String resetPassword,
                                 String confirmPassword) throws Exception {
        //校验输入
        if (!StringUtils.hasText(resetPassword) || !StringUtils.hasText(confirmPassword)){
            logger.error("resetPassword="+resetPassword, "confirmPassword="+confirmPassword);
            return false;
        }
        if (!confirmPassword.equals(resetPassword)){
            logger.error("两次输入的密码不相等");
            return false;
        }
        // 重置az密码
        return this.resetPassword(currentUser.getEmail(), resetPassword);
    }

    private boolean resetPassword(String username, String password) throws Exception{
        boolean flag1 = azerothService.updatePassword(username, password);
        if (flag1){
            logger.info("更新azeroth密码成功");
        }else {
            logger.error("更新azeroth密码失败");
            throw new Exception("更新azeroth密码失败: "+username);
        }
        // 重置账户密码
        String encodePassword = encoder.encode(password);
        boolean flag2 = iaccountService.updateUserPassword(username, encodePassword);
        if (flag2){
            logger.info("更新账户密码成功");
        }else{
            logger.error("更新账户密码失败");
            throw new Exception("更新账户密码失败: "+username);
        }
        return true;
    }

    @GlobalTransactional
    public boolean updatePassword(CustomUser currentUser, String originalPassword, String newPassword,
                                  String repeatNewPassword) throws Exception {
        //校验输入
        if (!StringUtils.hasText(originalPassword) || !StringUtils.hasText(newPassword)
                || !StringUtils.hasText(repeatNewPassword)){
            logger.error("originalPassword="+originalPassword, "newPassword="+newPassword,
                    "repeatNewPassword="+repeatNewPassword);
            return false;
        }
        if (!repeatNewPassword.equals(newPassword)){
            logger.error("两次输入的密码不相等");
            return false;
        }
        //校验输入与原始密码是否相等 TODO 用盐加密时
        return this.resetPassword(currentUser.getEmail(), newPassword);
    }


}
