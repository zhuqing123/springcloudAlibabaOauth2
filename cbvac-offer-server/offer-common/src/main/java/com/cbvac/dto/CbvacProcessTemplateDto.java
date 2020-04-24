package com.cbvac.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-21-15:21
 * @Description:
 */
@Data
@ApiModel(description = "工序模板")
public class CbvacProcessTemplateDto implements Serializable {

    /**
     * 工序名称
     */
    @ApiModelProperty(value = "工序名称")
    @ExcelProperty(value = "工序名称")
    private String processName;

    /**
     * 工序单价
     */
    @ApiModelProperty(value = "单价/元")
    @ExcelProperty(value = "单价/元")
    private BigDecimal processPrice;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    @ExcelProperty(value = "单位")
    private String dictUnitName;
}
