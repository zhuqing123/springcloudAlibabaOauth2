package com.cbvac.enums;

/**
 * @Author: zhuqing
 * @Date: 2020-04-09-17:13
 * @Description:
 */
public enum ResultEnum {

    SUCCESS(200, "操作成功"),

    FAIL(-1, "操作失敗"),

    UNAUTHORIZED(401, "登录失效"),

    FORBIDDEN(403, "权限不足"),

    DATA_NOT_FOUND(501, "数据不存在"),
    /******************offer-server服务错误提示信息以2000开始*****************************/
    DICT_TYPE_NOT_FOUND(2000, "选择的字典类型不存在"),

    DICT_VALUE_REPEAT(2001, "字典值重复"),

    DICT_NOT_FOUND(2003, "字典不存在"),

    MATERIAL_TYPE_EXIST(2004, "同规格的材质类型已经存在，不允许重复添加"),

    MATERIAL_TYPE_NOT_FOUND(2005, "材质类型不存在"),

    PROCESS_NOT_FOUND(2006, "工序不存在"),

    STANDARD_PARTS_EXIST(2007, "已经存在同规格的标准器"),

    PROJECT_NOT_FOUND(2008, "项目不存在"),

    MATERIAL_DENSITY_NOT_FOUND(2002, "材质密度不存在");
    /******************auth-server服务错误提示信息以1000开始*****************************/


    private int code;

    private String msg;

    private ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
