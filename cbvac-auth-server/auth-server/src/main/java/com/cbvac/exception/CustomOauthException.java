package com.cbvac.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-17:50
 * @Description:
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
    /**  */
    private static final long serialVersionUID = 1L;

    public CustomOauthException(String msg) {
        super(msg);
    }
}

