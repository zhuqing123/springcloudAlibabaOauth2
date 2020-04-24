package com.cbvac.config.oauth;

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

        http.requestMatcher(new OAuth2RequestedMatcher()).authorizeRequests().antMatchers(PermitAllUrl.permitAllUrl()).permitAll() // 放开权限的url
                .anyRequest().authenticated();
    }

    /**
     * @see ResourceServerConfigurerAdapter#configure(ResourceServerSecurityConfigurer)
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }

    /**
     * 判断来源请求是否包含oauth2授权信息<br>
     * url参数中含有access_token,或者header里有Authorization
     */
    private static class OAuth2RequestedMatcher implements RequestMatcher {
        @Override
        public boolean matches(HttpServletRequest request) {
            // 请求参数中包含access_token参数
            if ( StringUtils.isNoneBlank(request.getParameter(OAuth2AccessToken.ACCESS_TOKEN))) {
                return true;
            }
            // 头部的Authorization值以Bearer开头
            String auth = request.getHeader("Authorization");
            if (StringUtils.isNoneBlank(auth)) {
                return auth.startsWith(OAuth2AccessToken.BEARER_TYPE);
            }
            return false;
        }
    }
}
