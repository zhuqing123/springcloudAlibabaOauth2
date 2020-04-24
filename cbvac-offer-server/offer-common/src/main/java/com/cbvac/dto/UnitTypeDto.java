package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-16:54
 * @Description:
 */
@Data
@ApiModel(description = "新增计量单位")
public class UnitTypeDto implements Serializable {


    /**
     * 字典name
     */
    @ApiModelProperty(value = "字典名称，最大长度不超过32",required = true)
    @NotNull(message = "请输入字典名称")
    @Size(max = 32,message = "输入字典名称最大长度不能超过32")
    private String dictName;

    /**
     * 字典value
     */
    @ApiModelProperty(value = "字典编码，最大长度不超过20",required = true)
    @NotNull(message = "请输入字典编码")
    @Size(max = 32,message = "输入字典编码最大长度不能超过32")
    private String dictValue;

    /**
     * 父id
     */
    @ApiModelProperty(value = "单位类型id",required = true)
    @NotNull(message = "请选择单位类型")
    private Long parentId;

    /**
     * 描述
     */
    @ApiModelProperty(value = "字典描述，最大长度不超过100")
    @Size(max = 100,message = "输入字典描述最大长度不能超过100")
    private String description;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序，最大值不超过9999")
    @Max(value = 9999,message = "排序值最大不超过9999")
    private Integer sort;

}
