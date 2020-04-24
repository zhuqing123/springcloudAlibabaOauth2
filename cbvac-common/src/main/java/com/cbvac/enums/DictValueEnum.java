package com.cbvac.enums;

import org.apache.commons.lang3.StringUtils;
import sun.dc.pr.PRError;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-10:35
 * @Description:
 */
public enum DictValueEnum {

    ENABLE(DictTypeEnum.STATUS_TYPE.getType(), "1", "启用"),

    DISABLE(DictTypeEnum.STATUS_TYPE.getType(), "0", "停用"),

    CUTSOURCE_CUSTOM(DictTypeEnum.PRODUCTION_TYPE.getType(), "0", "外购定制"),

    HOMEMADE_STANDARD(DictTypeEnum.PRODUCTION_TYPE.getType(), "1", "自制标准件"),

    SELF_MADE_PARTS(DictTypeEnum.PRODUCTION_TYPE.getType(), "2", "自制定制件"),

    OUTSOURCE_STANDARD_PARTS(DictTypeEnum.PRODUCTION_TYPE.getType(), "3", "外购标准件"),

    WHOLE_OUTSOURCE(DictTypeEnum.PRODUCTION_TYPE.getType(), "4", "整体外协"),

    OUTSOURCE_STAND_HOMEMADE(DictTypeEnum.PRODUCTION_TYPE.getType(), "5", "外购标准+自制"),

    BUY_OUT_MAKE_SELF(DictTypeEnum.PRODUCTION_TYPE.getType(), "6", "外购定制+自制"),

    EX_COO_SELF_CONTROL(DictTypeEnum.PRODUCTION_TYPE.getType(), "7", "整体外协+自制"),

    SYSTEM_INTEGRATION(DictTypeEnum.PRODUCT_TYPE.getType(), "0", "系统集成"),

    CUSTOM_MADE(DictTypeEnum.PRODUCT_TYPE.getType(), "1", "定制品");

    private DictValueEnum(String dictType, String code, String describe) {
        this.dictType = dictType;
        this.code = code;
        this.describe = describe;
    }

    private String dictType;

    private String code;

    private String describe;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCodeToInt() {
        return Integer.valueOf(this.code);
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public static Integer getCodeValue(DictValueEnum dictValueEnum) {
        return Integer.valueOf(dictValueEnum.getCode());
    }

    public static DictValueEnum findByTypeAndCode(DictTypeEnum dictTypeEnum, Object code) {
        String type = dictTypeEnum.getType();
        DictValueEnum[] values = values();
        return Arrays.stream(values)
                .filter(dictValueEnum -> StringUtils.equalsIgnoreCase(type, dictValueEnum.getDictType()) && StringUtils.equals(String.valueOf(code), dictValueEnum.getCode())).findFirst().orElse(null);
    }

    public static List<DictValueEnum> findByDictType(DictTypeEnum dictTypeEnum) {
        return Arrays.stream(values())
                .filter(dictValueEnum -> StringUtils.equals(dictValueEnum.getDictType(), dictTypeEnum.getType()))
                .collect(Collectors.toList());
    }
}
