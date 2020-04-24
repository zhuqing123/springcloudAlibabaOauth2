package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-9:07
 * @Description:
 */
@Data
@ApiModel(description = "字典查询参数")
public class CbvacDictPageDto extends PageDto {

    /**
     * 字典name
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    /**
     * 字典value
     */
    @ApiModelProperty(value = "字典value")
    private String dictValue;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父value", hidden = true)
    private String parentValue;

    @ApiModelProperty(value = "批量父value", hidden = true)
    private List<String> parentValues;
}
