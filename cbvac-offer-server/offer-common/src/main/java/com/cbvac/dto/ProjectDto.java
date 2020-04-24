package com.cbvac.dto;

import com.cbvac.annotation.DictValue;
import com.cbvac.enums.DictTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: zhuqing
 * @Date: 2020-04-24-13:36
 * @Description:
 */
@Data
@ApiModel(description = "新增项目")
public class ProjectDto implements Serializable {

    /**
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称，输入项目名称最大长度64",required = true)
    @NotBlank(message = "请输入项目名称")
    @Size(max = 64,message = "输入项目名称最大长度64")
    private String projectName;

    /**
     * 项目类型
     */
    @ApiModelProperty(value = "项目类型",required = true)
    @NotBlank(message = "请选择项目类型")
    @DictValue(dictType = DictTypeEnum.PRODUCT_TYPE,message = "请选择正确的项目类型")
    private Integer projectType;

    /**
     * 包装费
     */
    @ApiModelProperty(value = "包装费，整数位最多八位，小数位最多两位",required = true)
    @NotNull(message = "请输入包装费")
    @Digits(integer = 8, fraction = 2, message = "包装费整数位最多八位，小数位最多两位")
    private BigDecimal packing;

    /**
     * 运输费
     */
    @ApiModelProperty(value = "运输费，整数位最多八位，小数位最多两位",required = true)
    @NotNull(message = "请输入运输费")
    @Digits(integer = 8, fraction = 2, message = "运输费整数位最多八位，小数位最多两位")
    private BigDecimal transportation;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注，输入备注最大长度200")
    @Size(max = 200,message = "输入备注最大长度200")
    private String remarks;
}
