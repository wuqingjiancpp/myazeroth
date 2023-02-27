package com.accendl.mail;

import com.accendl.mail.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;

@SpringBootTest
public class TestUserService {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testSendMail() throws Exception {
        userService.sendQROfGoogleAuthenticator("crazywu@126.com", "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK");
    }
}
