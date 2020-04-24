package com.cbvac.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import java.util.UUID;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-16:38
 * @Description:
 */
public class RandomAuthenticationKeyGenerator implements AuthenticationKeyGenerator {

    /**
     * @see org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator#extractKey(org.springframework.security.oauth2.provider.OAuth2Authentication)
     */
    @Override
    public String extractKey(OAuth2Authentication authentication) {
        return UUID.randomUUID().toString();
    }
}
