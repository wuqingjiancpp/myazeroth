package com.accendl.myweibo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class MyweiboApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyweiboApplication.class, args);
    }

}
