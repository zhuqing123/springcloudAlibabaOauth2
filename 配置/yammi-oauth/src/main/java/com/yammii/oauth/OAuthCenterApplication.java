package com.yammii.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * 认证中心启动类
 * 
 * @author yongyan.pu
 * @version $Id: OAuthCenterApplication.java, v 0.1 2018年12月18日 上午9:22:31 yongyan.pu Exp $
 */
@Configuration
@EnableFeignClients(basePackages = { "com.yammii.*.api.feign" })
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "com.yammii.*" })
public class OAuthCenterApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(OAuthCenterApplication.class, args);
    }

}