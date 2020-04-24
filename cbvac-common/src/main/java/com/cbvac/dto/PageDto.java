package com.cbvac.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-8:43
 * @Description:
 */
@ApiModel(description = "分页参数")
public class PageDto implements Serializable {

    @ApiModelProperty(value = "当前页,当前页最小为1最大为9999")
    @Range(min = 1, max = 9999, message = "当前页最小为1最大为9999")
    private Integer currentPage = 1;

    @ApiModelProperty(value = "页大小,每页大小最少5条数据，最多100条")
    @Range(min = 5, max = 100, message = "每页大小最少5条数据，最多100条")
    private Integer pageSize = 5;

    @ApiModelProperty(value = "排序字段")
    private String sort = "create_time";

    @ApiModelProperty(value = "排序方式，降序'desc',升序'asc' ", allowableValues = "desc,asc")
    private String sortWay = "desc";


    @ApiModelProperty(hidden = true)
    @ApiIgnore
    public Page getPage() {
        Page page = new Page(this.currentPage, this.pageSize);
        OrderItem orderItem = null;
        if (StringUtils.equalsIgnoreCase(this.sortWay, "desc")) {
            orderItem = OrderItem.desc(this.sort);
        } else {
            orderItem = OrderItem.asc(this.sort);
        }
        page.addOrder(orderItem);
        return page;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        if (StringUtils.isNoneBlank(sort)) {
            this.sort = sort;
        } else {
            sort = "create_time";
        }

    }

    public String getSortWay() {
        return sortWay;
    }

    public void setSortWay(String sortWay) {
        if (StringUtils.isNoneBlank(sortWay)) {
            this.sortWay = sortWay;
        } else {
            sortWay = "desc";
        }
    }
}
