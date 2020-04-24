package com.cbvac;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.druid.DruidConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @Author: zhuqing
 * @Date: 2020-04-10-16:53
 * @Description:
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
@MapperScan(value = "com.cbvac.mapper")
public class OfferServerApplication {

    public static void main(String[] args) {
        System.out.println(SpringApplication.run(OfferServerApplication.class, args));
    }
}
