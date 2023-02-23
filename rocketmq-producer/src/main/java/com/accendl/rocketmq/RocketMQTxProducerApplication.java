package com.accendl.rocketmq;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class RocketMQTxProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQTxProducerApplication.class, args);
    }

}
