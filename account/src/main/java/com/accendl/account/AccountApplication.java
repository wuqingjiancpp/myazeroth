package com.accendl.account;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
@EnableDubbo
public class AccountApplication {

    public static void main(String[] args) throws Exception{
//       ApplicationContext applicationContext =
               SpringApplication.run(AccountApplication.class, args);
//        ServerServiceImpl serverService = applicationContext.getBean(ServerServiceImpl.class);
//        System.out.println(serverService.info());
    }

}
