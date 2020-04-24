package com.cbvac.exception;

import com.cbvac.enums.ResultEnum;
import com.cbvac.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: zhuqing
 * @Date: 2020-04-10-15:53
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    public ResultVo exceptionHandler(Exception e) {
        LOGGER.error("全局异常处理", e);
        return new ResultVo(ResultEnum.FAIL);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResultVo customException(CustomException e) {
        LOGGER.error("全局异常处理", e);
        return new ResultVo(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    public ResultVo bindException(BindException e) {
        LOGGER.error("全局异常处理", e);
        String msg = e.getAllErrors().get(0).getDefaultMessage();
        return new ResultVo(ResultEnum.FAIL.getCode(), msg);
    }
}
