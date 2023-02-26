package com.accendl.myweibo.service;

import com.accendl.account.dto.UserDTO;
import com.accendl.rocketmq.service.IAccountService;
import com.accendl.rocketmq.service.IAzerothService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RocketMQService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @DubboReference(version = "1.0.0")
    private IAccountService accountService;

    @DubboReference(version = "1.0.0")
    private IAzerothService azerothService;

    public void sendBase32Key(String username, String base32key) throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setBase32Key(base32key);
        boolean flag = accountService.sendBase32Key(userDTO);
        if (flag){
            logger.info("raccountService.sendBase32Key success");
        }else {
            logger.info("raccountService.sendBase32Key fail");
        }
    }

    /**
     * Account created: user11@example.com
     * Account with this name already exist!
     *
     * @param username
     * @param password
     * @return
     */
    public boolean accountCreate(String username, String password) throws Exception {
        boolean flag = azerothService.accountCreate(username, password);
        if (flag){
            logger.info("创建账户成功");
            return true;
        }else{
            logger.error("世界服务器错误");
            return false;
        }
    }
}
