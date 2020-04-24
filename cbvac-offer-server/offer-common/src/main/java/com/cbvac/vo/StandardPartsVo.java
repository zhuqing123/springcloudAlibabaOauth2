package com.cbvac.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-23-15:06
 * @Description:
 */
@Data
@ApiModel(description = "自制标准，外购标准返回实体")
public class StandardPartsVo extends BaseEntityVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "部件名称")
    private String partsName;

    @ApiModelProperty(value = "规格型号")
    private String specifications;


    @ApiModelProperty(value = "单价")
    private BigDecimal price;


    @ApiModelProperty(value = "单位")
    private Long dictUnitId;

    @ApiModelProperty(value = "单位name")
    private String dictUnitName;


    @ApiModelProperty(value = "品牌")
    private String brand;

    @ApiModelProperty(value = "1自制标准件3外购标准件")
    private Integer type;

}
