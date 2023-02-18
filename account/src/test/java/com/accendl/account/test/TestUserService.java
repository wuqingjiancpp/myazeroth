package com.accendl.account.test;

import com.accendl.account.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class TestUserService {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testFindUserByEmail() throws Exception {
        Assert.notNull(userService.findCustomUserByEmail("user@example.com"), "用户不存在");
    }
}
