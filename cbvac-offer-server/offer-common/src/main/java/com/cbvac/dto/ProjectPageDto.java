package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhuqing
 * @Date: 2020-04-24-11:36
 * @Description:
 */
@Data
@ApiModel(description = "项目查询类")
public class ProjectPageDto extends PageDto {

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    private String projectName;

    /**
     * 产品类型
     */
    @ApiModelProperty(value = "产品类型,0系统集成，1定制品", allowableValues = "0,1")
    private Integer projectType;
}
