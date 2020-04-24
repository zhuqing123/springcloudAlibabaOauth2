package com.cbvac.config;

import com.cbvac.constant.PermitAllUrl;
import com.cbvac.exception.AuthExceptionEntryPoint;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-16:35
 * @Description:
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * @see ResourceServerConfigurerAdapter#configure(HttpSecurity)
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.requestMatchers().anyRequest().and().authorizeRequests().antMatchers(PermitAllUrl.permitAllUrl()).permitAll() // 放开权限的url
                .anyRequest().authenticated();
    }

    /**
     * @see ResourceServerConfigurerAdapter#configure(ResourceServerSecurityConfigurer)
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }
}
