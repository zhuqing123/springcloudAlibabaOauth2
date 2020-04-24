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
 * @Date: 2020-04-23-15:55
 * @Description:
 */
@Data
@ApiModel(description = "自制标准件操作实体")
public class StandardPartsDto implements Serializable {

    /**
     * 部件名称
     */
    @ApiModelProperty(value = "部件名称,部件名称输入最大长度为64", required = true)
    @NotBlank(message = "请输入部件名称")
    @Size(max = 64, message = "部件名称输入最大长度为64")
    private String partsName;

    /**
     * 规格型号
     */
    @ApiModelProperty(value = "规格型号,规格型号输入最大长度为64", required = true)
    @NotBlank(message = "请输入规格型号")
    @Size(max = 64, message = "规格型号输入最大长度为64")
    private String specifications;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价，价格整数位最多八位，小数位最多两位", required = true)
    @NotNull(message = "请输入单价")
    @Digits(integer = 8, fraction = 2, message = "价格整数位最多八位，小数位最多两位")
    private BigDecimal price;

    /**
     * 字典id
     */
    @ApiModelProperty(value = "计量单位id", required = true)
    @NotNull(message = "请选择计量单位")
    private Long dictUnitId;

    /**
     * 1自制标准件3外购标准件
     */
    @ApiModelProperty(value = "1自制标准件3外购标准件", hidden = true)
    private Integer type;
}
