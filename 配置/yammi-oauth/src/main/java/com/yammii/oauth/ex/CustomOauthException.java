package com.yammii.oauth.ex;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * 
 * @author yongyan.pu
 * @version $Id: CustomOauthException.java, v 0.1 2019年1月15日 下午1:34:16 yongyan.pu Exp $
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    /**  */
    private static final long serialVersionUID = 1L;

    public CustomOauthException(String msg) {
        super(msg);
    }
}
