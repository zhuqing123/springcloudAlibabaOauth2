package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @Author: zhuqing
 * @Date: 2020-04-23-16:03
 * @Description:
 */
@Data
@ApiModel(description = "外购标准品操作实体")
public class StandardPartsOutDto extends StandardPartsDto {


    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌,品牌输入最大长度为64", required = true)
    @NotBlank(message = "请输入品牌")
    @Size(max = 64, message = "品牌输入最大长度为64")
    private String brand;
}
