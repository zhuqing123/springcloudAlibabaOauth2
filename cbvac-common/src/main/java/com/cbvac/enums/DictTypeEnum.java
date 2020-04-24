package com.cbvac.enums;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-10:50
 * @Description:
 */
public enum DictTypeEnum {

    /**
     * 生产类型
     */
    PRODUCTION_TYPE("PRODUCTION_TYPE", 0, "生产类型"),

    STATUS_TYPE("STATUS_TYPE", 0, "启停状态"),

    RAW_MATERIAL_TYPE("RAW_MATERIAL_TYPE", 0, "原材料类型"),

    PRODUCT_TYPE("PRODUCT_TYPE", 0, "项目类型"),
    /****************************计量单位配置start**************************/
    WEIGHT_UNIT("1381", 1, "重量单位"),

    LENGTH_UNIT("1384", 1, "长度单位"),

    VOLUME_UNIT("1387", 1, "容积单位"),

    NUMBER_UNIT("1390", 1, "数量单位"),

    AREA_UNIT("1417", 1, "面积单位");

    /****************************计量单位配置end**************************/


    /**
     * 不允许重复
     */
    private String type;

    /**
     * 类型归类区分，不用区分默认是0
     */
    private Integer code;

    /**
     * 描述
     */
    private String description;

    DictTypeEnum(String type, Integer code, String description) {
        this.type = type;
        this.code = code;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 根据code分类，查询相同类型的字典value
     *
     * @param code
     * @return
     */
    public static List<DictTypeEnum> findByCode(Integer code) {
        return Arrays.stream(values()).filter(dictTypeEnum -> dictTypeEnum.getCode().equals(code)).collect(Collectors.toList());
    }

    /**
     * 根据type得到字典value
     *
     * @param type
     * @return
     */
    public static DictTypeEnum findByType(String type) {
        return Arrays.stream(values()).filter(dictTypeEnum -> StringUtils.equals(dictTypeEnum.getType(), type)).findFirst().orElse(null);
    }
}
