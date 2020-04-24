package com.yammii.oauth.service.impl;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;

import java.util.UUID;

/**
 * 
 * token重写
 * @author yongyan.pu
 * @version $Id: RandomAuthenticationKeyGenerator.java, v 0.1 2019年1月22日 下午4:51:34 yongyan.pu Exp $
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
