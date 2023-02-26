package com.accendl.myweibo.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

@SpringBootTest
public class TestAccountService {
    @Test
    public void testPattern(){
        System.out.println(Pattern.matches("([\\w\\-]+\\@[\\w\\-]+\\.[\\w\\-]+)", "user@example.com"));
        System.out.println(Pattern.matches("([\\w\\-]+\\@[\\w\\-]+\\.[\\w\\-]+)", "user@example."));
        System.out.println(Pattern.matches("([\\w\\-]+\\@[\\w\\-]+\\.[\\w\\-]+)", "user@example"));
    }
}
