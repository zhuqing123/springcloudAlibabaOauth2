package com.cbvac.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-20-17:10
 * @Description:
 */
@Data
@ApiModel(description = "密度导入模板表")
public class CbvacMaterialDensityTemplateDto implements Serializable {

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
     * 密度
     */
    @ApiModelProperty(value = "密度")
    @ExcelProperty(value = "密度")
    private BigDecimal density;

}
