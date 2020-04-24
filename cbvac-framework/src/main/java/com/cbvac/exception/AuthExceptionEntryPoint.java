package com.cbvac.exception;

import com.alibaba.fastjson.JSON;
import com.cbvac.enums.ResultEnum;
import com.cbvac.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-17:31
 * @Description:
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    public static final Logger LOGGER= LoggerFactory.getLogger(AuthExceptionEntryPoint.class);


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        LOGGER.error("登录失效",e);
        LOGGER.info("url地址：{}",request.getRequestURI());
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        ResultVo resultVo=new ResultVo(ResultEnum.UNAUTHORIZED);
        response.getWriter().write(JSON.toJSONString(resultVo));
    }
}
