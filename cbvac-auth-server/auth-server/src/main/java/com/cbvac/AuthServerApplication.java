package com.cbvac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-9:57
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(value = "com.cbvac.mapper")
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class,args);
    }
}
