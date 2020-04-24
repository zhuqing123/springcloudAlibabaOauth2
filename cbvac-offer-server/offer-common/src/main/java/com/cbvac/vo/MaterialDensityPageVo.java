package com.cbvac.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-17:11
 * @Description:
 */
@Data
@ApiModel(description = "材质密度列表返回实体")
public class MaterialDensityPageVo extends BaseEntityVo {

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
     * 密度
     */
    @ApiModelProperty(value = "密度")
    private BigDecimal density;

}
