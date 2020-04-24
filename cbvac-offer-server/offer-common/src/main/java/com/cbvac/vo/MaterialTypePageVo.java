package com.cbvac.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-23-8:43
 * @Description:
 */
@Data
@ApiModel(description = "材质类型分页列表")
public class MaterialTypePageVo extends BaseEntityVo {

    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 材质类型
     */
    @ApiModelProperty(value = "材质类型")
    private String materialType;

    /**
     * 材质名称
     */
    @ApiModelProperty(value = "材质名称")
    private String materialName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specifications;

    /**
     * 字典表id
     */
    @ApiModelProperty(value = "单位id")
    private Long dictUnitId;

    @ApiModelProperty(value = "单位name")
    private String dictUnitName;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    /**
     * 0停用1启用
     */
    @ApiModelProperty(value = "启停状态")
    private Integer status;

    /**
     * 0停用1启用
     */
    @ApiModelProperty(value = "启停状态中文")
    private String statusValue;

    /**
     * 0停用1启用
     */
    @ApiModelProperty(value = "启停状态按钮展示")
    private String statusBtnValue;

    @ApiModelProperty(value = "密度")
    private BigDecimal density;
}
