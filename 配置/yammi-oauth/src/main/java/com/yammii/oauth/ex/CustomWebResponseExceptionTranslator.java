package com.yammii.oauth.ex;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author yongyan.pu
 * @version $Id: CustomWebResponseExceptionTranslator.java, v 0.1 2019年1月15日 下午1:35:20 yongyan.pu Exp $
 */
@SuppressWarnings("rawtypes")
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
