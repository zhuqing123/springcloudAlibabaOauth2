package com.cbvac.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-17:50
 * @Description:
 */
@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

        if (e instanceof OAuth2Exception) {
            OAuth2Exception oAuth2Exception = (OAuth2Exception) e;
            return ResponseEntity.status(oAuth2Exception.getHttpErrorCode())
                    .body(new CustomOauthException(oAuth2Exception.getMessage()));
        }
        return ResponseEntity.status(401).body(new CustomOauthException(e.getMessage()));
    }
}
