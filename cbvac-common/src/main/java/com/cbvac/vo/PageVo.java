package com.cbvac.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-9:59
 * @Description:
 */
@Data
@ApiModel(description = "分页返回")
public class PageVo<T> implements Serializable {

    @ApiModelProperty(value = "数据")
    private List<T> records = new ArrayList<>();

    @ApiModelProperty(value = "总条数")
    private Integer total;

    @ApiModelProperty(value = "当前页")
    private Integer currentPage;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "总页数")
    private Integer pages;

    private Boolean hitCount;

    private Boolean searchCount;
}
