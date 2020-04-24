package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhuqing
 * @Date: 2020-04-23-14:58
 * @Description:
 */
@Data
@ApiModel(description = "自制标准品与外购标准品查询实体")
public class StandardPartsPageDto extends PageDto {

    /**
     * 部件名称
     */
    @ApiModelProperty(value = "部件名称")
    private String partsName;

    /**
     * 规格型号
     */
    @ApiModelProperty(value = "规格型号")
    private String specifications;

    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌,自制标准品不用填写，外购标准品要填")
    private String brand;

    /**
     * 1自制标准件3外购标准件
     */
    @ApiModelProperty(value = " 1自制标准件3外购标准件", hidden = true)
    private Integer type;
}
