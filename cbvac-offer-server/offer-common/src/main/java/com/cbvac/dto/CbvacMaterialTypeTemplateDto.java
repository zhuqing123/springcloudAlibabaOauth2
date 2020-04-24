package com.cbvac.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-20-22:47
 * @Description:
 */
@Data
@ApiModel(description = "材质类型模板")
public class CbvacMaterialTypeTemplateDto implements Serializable {

    /**
     * 材质类型
     */
    @ApiModelProperty(value = "材质类型")
    @ExcelProperty(value = "材质类型")
    private String materialType;

    /**
     * 材质名称
     */
    @ApiModelProperty(value = "材质名称")
    @ExcelProperty(value = "材质名称")
    private String materialName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    @ExcelProperty(value = "规格")
    private String specifications;

    /**
     * 字典表id
     */
    @ApiModelProperty(value = "单位")
    @ExcelProperty(value = "单位")
    private String dictName;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    @ExcelProperty(value = "单价")
    private BigDecimal price;
}
