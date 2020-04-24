package com.cbvac.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zhuqing
 * @Date: 2020-04-23-8:47
 * @Description:
 */
@Data
@ApiModel(description = "返回值父类")
public class BaseEntityVo implements Serializable {


    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改人id
     */
    @ApiModelProperty(value = "修改人id")
    private Long modifyId;

    /**
     * 修改人名称
     */
    @ApiModelProperty(value = "修改人名称")
    private String modifyName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private Long createId;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createName;
}
