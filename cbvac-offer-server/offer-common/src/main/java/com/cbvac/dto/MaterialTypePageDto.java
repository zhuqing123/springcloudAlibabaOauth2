package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhuqing
 * @Date: 2020-04-23-8:40
 * @Description:
 */
@Data
@ApiModel(description = "材质类型分页查询实体")
public class MaterialTypePageDto extends PageDto {


    /**
     * 材质类型
     */
    @ApiModelProperty(value = "材质类型")
    private String materialType;

    /**
     * 材质名称
     */
    @ApiModelProperty(value = "材质名称")
    private String materialName;

    /**
     * 规格
     */
    @ApiModelProperty(value = "规格")
    private String specifications;
}
