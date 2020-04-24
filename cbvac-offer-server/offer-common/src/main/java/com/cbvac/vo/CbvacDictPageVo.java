package com.cbvac.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-10:30
 * @Description:
 */
@Data
@ApiModel(description = "字典列表数据返回")
public class CbvacDictPageVo extends BaseEntityVo {


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
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

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
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    /**
     * 0停用1启用
     */
    @ApiModelProperty(value = "启停状态按钮展示")
    private String statusBtnValue;

    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private Long parentId;

    /**
     * 父value
     */
    @ApiModelProperty(value = "父value")
    private String parentValue;

    /**
     * 父名称
     */
    @ApiModelProperty(value = "父名称")
    private String parentName;
}
