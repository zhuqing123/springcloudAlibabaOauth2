package com.cbvac.vo;

import com.cbvac.enums.ResultEnum;
import lombok.Data;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-17:10
 * @Description:
 */
@Data
public class ResultVo<T> {

    private int code;

    private String msg;

    private T data;

    public ResultVo(){
    }

    public ResultVo(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public ResultVo(int code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public ResultVo(ResultEnum resultEnum){
        this.code=resultEnum.getCode();
        this.msg=resultEnum.getMsg();
    }

    public ResultVo(ResultEnum resultEnum,T data){
        this.code=resultEnum.getCode();
        this.msg=resultEnum.getMsg();
        this.data=data;
    }

    public static ResultVo success(){
      return new ResultVo(ResultEnum.SUCCESS);
    }

    public static ResultVo fail(){
        return new ResultVo(ResultEnum.FAIL);
    }

}
