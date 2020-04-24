package com.yammii.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yongyan.pu
 * @version $Id: SwaggerConfig.java, v 0.1 2019年1月12日 下午3:47:52 yongyan.pu Exp $
 */
@Configuration
@EnableSwagger2
@Profile({ "dev", "local", "test" })
public class SwaggerConfig {
   

    @Bean
    public Docket app() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("认证中心").apiInfo(apiInfo("认证中心", "V1.0")).select()
            .apis(RequestHandlerSelectors.basePackage("com.yammii.oauth.controller")).paths(PathSelectors.any()).build();
    }

    /**
    * 构建apiInfo
    * @param title
    * @param version
    * @return ApiInfo
    */
    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder().title(title).version(version).build();
    }
}
