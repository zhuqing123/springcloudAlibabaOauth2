package com.cbvac.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-24-11:40
 * @Description:
 */
@Data
@ApiModel(description = "项目返回实体")
public class ProjectVo extends BaseEntityVo {

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "产品类型")
    private Integer projectType;

    @ApiModelProperty(value = "产品类型name")
    private String projectTypeName;

    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    @ApiModelProperty(value = "包装费")
    private BigDecimal packing;

    @ApiModelProperty(value = "运输费")
    private BigDecimal transportation;

    @ApiModelProperty(value = "材料费")
    private BigDecimal materialsPrice;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
