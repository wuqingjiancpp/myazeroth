package com.accendl.mail;

import com.accendl.mail.service.impl.UserServiceImpl;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserService {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testSendMail() throws Exception {
        userService.sendQROfGoogleAuthenticator("wu_qingjian@126.com", "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK");
    }
}
