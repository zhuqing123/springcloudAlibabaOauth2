package com.cbvac.exception;

import com.cbvac.enums.ResultEnum;

/**
 * @Author: zhuqing
 * @Date: 2020-04-10-15:55
 * @Description:
 */
public class CustomException extends RuntimeException {

    private int code;

    public CustomException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code=resultEnum.getCode();
    }

    public int getCode() {
        return code;
    }
}
