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
 * @Date: 2020-04-23-13:55
 * @Description:
 */
@Data
@ApiModel(description = "工序操作实体")
public class ProcessDto implements Serializable {

    /**
     * 工序名称
     */
    @ApiModelProperty(value = "工序名称，工序名称输入最大长度不能超过64位", required = true)
    @NotBlank(message = "请输入工序名称")
    @Size(max = 64, message = "工序名称输入最大长度不能超过64位")
    private String processName;

    /**
     * 工序单价
     */
    @ApiModelProperty(value = "单价，价格整数位最多八位，小数位最多两位", required = true)
    @NotNull(message = "请输入单价")
    @Digits(integer = 8, fraction = 2, message = "价格整数位最多八位，小数位最多两位")
    private BigDecimal processPrice;

    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id", required = true)
    @NotNull(message = "请选择计量单位")
    private Long dictUnitId;
}
