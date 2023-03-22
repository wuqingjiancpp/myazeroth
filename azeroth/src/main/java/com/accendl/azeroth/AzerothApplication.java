package com.accendl.azeroth;

import com.accendl.azeroth.service.impl.ServerServiceImpl;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

@SpringBootApplication
@EnableDubbo
@EnableDiscoveryClient
public class AzerothApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(AzerothApplication.class, args);
    }

}
