package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-17:09
 * @Description:
 */
@Data
@ApiModel(description = "材质密度")
public class MaterialDensityPageDto extends PageDto {


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
}
