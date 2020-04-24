package com.yammii.common.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammii.common.exception.GlobalErrorCode;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author yongyan.pu
 * @version $Id: AuthExceptionEntryPoint.java, v 0.1 2019年5月31日 下午4:39:01 yongyan.pu Exp $
 */
@Slf4j
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException,
                                                                ServletException {
        
        log.info("========================================>AuthExceptionEntryPoint",authException);
        Map<String, Object> map = new HashMap<>();
        Throwable cause = authException.getCause();
        if (cause instanceof InvalidTokenException) {
            map.put("status", GlobalErrorCode.INVALID_TOKEN.getCode());//401
            map.put("message", "Invalid access token:" + authException.getMessage());
        } else {
            log.info("");
            map.put("status", GlobalErrorCode.UNAUTHORIZED.getCode());//401
            map.put("message", "AccessDenied");
        }
        //        map.put("data", authException.getMessage());
        //        map.put("success", false);
        //        map.put("path", request.getServletPath());
        //        map.put("timestamp", String.valueOf(new Date().getTime()));
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}