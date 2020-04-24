package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhuqing
 * @Date: 2020-04-23-11:44
 * @Description:
 */
@Data
@ApiModel(description = "工序列表查询")
public class ProcessPageDto extends PageDto {

    /**
     * 工序名称
     */
    @ApiModelProperty(value = "工序名称")
    private String processName;
}
