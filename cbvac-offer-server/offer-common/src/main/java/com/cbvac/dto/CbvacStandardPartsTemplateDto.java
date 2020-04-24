package com.cbvac.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-21-17:50
 * @Description:
 */
@Data
@ApiModel(description = "自制标准件导入模板")
public class CbvacStandardPartsTemplateDto implements Serializable {

    /**
     * 部件名称
     */
    @ApiModelProperty(value = "部件名称")
    @ExcelProperty(value = "部件名称")
    private String partsName;

    /**
     * 规格型号
     */
    @ApiModelProperty(value = "规格型号")
    @ExcelProperty(value = "规格型号")
    private String specifications;

    /**
     * 单价/元
     */
    @ApiModelProperty(value = "单价/元")
    @ExcelProperty(value = "单价/元")
    private BigDecimal price;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    @ExcelProperty(value = "单位")
    private String dictUnitName;
}
