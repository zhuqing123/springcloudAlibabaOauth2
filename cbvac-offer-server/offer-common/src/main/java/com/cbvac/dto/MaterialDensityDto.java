package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-17:23
 * @Description:
 */
@Data
@ApiModel(description = "材质密度")
public class MaterialDensityDto implements Serializable {

    /**
     * 材质类型
     */
    @ApiModelProperty(value = "材质类型，材质类型最大长度不能超过32")
    @NotBlank(message = "请输入材质类型")
    @Size(max = 32, message = "输入材质类型最大长度不能超过32")
    private String materialType;

    /**
     * 材质名称
     */
    @ApiModelProperty(value = "材质名称，材质名称最大长度不能超过32")
    @NotBlank(message = "请输入材质名称")
    @Size(max = 32, message = "输入材质名称最大长度不能超过32")
    private String materialName;

    /**
     * 密度
     */
    @ApiModelProperty(value = "密度，密度整数最大长度为8小数最多两位")
    @NotNull(message = "请输入材质名称")
    @Digits(integer = 8,fraction = 2,message = "密度整数最大长度为8小数最多两位")
    private BigDecimal density;
}
