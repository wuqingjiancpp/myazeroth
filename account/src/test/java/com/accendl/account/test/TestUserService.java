package com.accendl.account.test;

import com.accendl.account.service.IUsersService;
import com.accendl.account.service.impl.UsersServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class TestUserService {

    @Autowired
    private UsersServiceImpl usersService;

    @Test
    public void testUserPassword(){
        usersService.getByName("test");
    }

    @Test
    public void testFindUserByEmail() throws Exception {
        Assert.notNull(usersService.findCustomUserByEmail("user@example.com"), "用户不存在");
    }
}
