package com.accendl.food;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
@EnableDubbo
public class FoodApplication {

    public static void main(String[] args) throws Exception{
        SpringApplication.run(FoodApplication.class, args);
    }

}
