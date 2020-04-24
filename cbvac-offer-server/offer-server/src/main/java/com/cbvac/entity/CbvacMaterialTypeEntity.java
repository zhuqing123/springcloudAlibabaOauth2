package com.cbvac.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cbvac.entity.BaseEntity;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 材质类型表
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cbvac_material_type")
public class CbvacMaterialTypeEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 密度表id
     */
    private Long densityId;

    /**
     * 字典表id
     */
    private Long dictUnitId;

    /**
     * 规格
     */
    private String specifications;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 启停状态1启用0停用
     */
    private Integer status;

    /**
     * 材质类型
     */
    @TableField(exist = false)
    private String materialType;

    /**
     * 材质名称
     */
    @TableField(exist = false)
    private String materialName;

    /**
     * 单位名称
     */
    @TableField(exist = false)
    private String dictUnitName;

    /**
     * 密度
     */
    @TableField(exist = false)
    private BigDecimal density;

}
