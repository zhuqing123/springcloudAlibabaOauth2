package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-23-10:05
 * @Description:
 */
@Data
@ApiModel(description = "材质类型新增列表")
public class MaterialTypeDto implements Serializable {

    /**
     * 密度表id
     */
    @ApiModelProperty(value = "密度表id",required = true)
    @NotNull(message = "请选择材质")
    private Long densityId;

    /**
     * 字典表id
     */
    @ApiModelProperty(value = "计量单位id",required = true)
    @NotNull(message = "请选择计量单位")
    private Long dictUnitId;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格，输入规格的最大长度不超过32",required = true)
    @NotBlank(message = "请输入规格")
    @Size(max =32,message = "输入规格的最大长度不超过32")
    private String specifications;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价，价格整数位最多八位，小数位最多两位",required = true)
    @NotNull(message = "请输入单价")
    @Digits(integer = 8,fraction = 2,message = "价格整数位最多八位，小数位最多两位")
    private BigDecimal price;
}
