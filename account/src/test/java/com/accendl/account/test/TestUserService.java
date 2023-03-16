package com.accendl.account.test;

import com.accendl.account.dto.UserDTO;
import com.accendl.account.entity.User;
import com.accendl.account.service.impl.UserServiceImpl;
import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;
import com.alibaba.com.caucho.hessian.io.JavaDeserializer;
import com.alibaba.com.caucho.hessian.io.JavaSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.*;

@SpringBootTest
public class TestUserService {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testUpdatePassword() throws Exception {
        String username = "user@example.com";
        String password = "pwd";
//        Assert.isTrue(userService.updatePassword(username, password), "更新影响条数为0");
    }

    @Test
    public void testFindUserByEmail() throws Exception {
//        Assert.notNull(userService.findCustomUserByEmail("user@example.com"), "用户不存在");
    }

    @Test
    public void testCreateUserDuplicatePK() throws Exception{
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUsername("user@example.com");
//        userDTO.setPassword("{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm");
//        userDTO.setSecret("80ed266dd80bcd32564f0f4aaa8d9b149a2b1eaa");
//        userDTO.setAnswer("{bcrypt}$2a$10$JIXMjAszy3RUu8y5T0zH0enGJCGumI8YE.K7w3wsM5xXDfeVIsJhq");
//        userDTO.setEnabled(true);
//        UserDTO tmp = userService.createUser(userDTO);
//        System.out.println("userDTO="+tmp.getId());
    }

    @Test
    public void testCreateUser() throws Exception{
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUsername("test@example.com");
//        userDTO.setPassword("password");
//        userDTO.setSecret("secret");
//        userDTO.setEnabled(true);
//        UserDTO tmp = userService.createUser(userDTO);
//        System.out.println("userDTO="+tmp.getId());
    }


}
