package com.cbvac.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-23-11:47
 * @Description:
 */
@Data
@ApiModel(description = "工序列表")
public class ProcessVo extends BaseEntityVo {

    /**
     * 工序名称
     */
    @ApiModelProperty(value = "工序名称")
    private String processName;

    /**
     * 工序单价
     */
    @ApiModelProperty(value = "工序单价")
    private BigDecimal processPrice;

    /**
     * 单位id
     */
    @ApiModelProperty(value = "单位id")
    private Long dictUnitId;


    @ApiModelProperty(value = "单位name")
    private String dictUnitName;

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
}
