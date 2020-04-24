package com.cbvac.exception;

import com.alibaba.fastjson.JSON;
import com.cbvac.enums.ResultEnum;
import com.cbvac.vo.ResultVo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-17:42
 * @Description:
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        ResultVo resultVo=new ResultVo(ResultEnum.FORBIDDEN);
        response.getWriter().write(JSON.toJSONString(resultVo));
    }
}
