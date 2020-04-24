package com.cbvac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-15:57
 * @Description:
 */
@Data
@ApiModel(description = "字典修改表")
public class CbvacDictEditDto implements Serializable {

    @ApiModelProperty(value = "主键", required = true)
    @NotNull(message = "请选择数据")
    private Long id;

    /**
     * 字典name
     */
    @ApiModelProperty(value = "字典名称，最大长度不超过32", required = true)
    @NotBlank(message = "请输入字典名称")
    @Size(max = 32, message = "输入字典名称最大长度不能超过32")
    private String dictName;
}
