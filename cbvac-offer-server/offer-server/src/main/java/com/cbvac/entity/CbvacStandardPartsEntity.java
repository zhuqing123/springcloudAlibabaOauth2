package com.cbvac.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cbvac.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 标准件
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cbvac_standard_parts")
public class CbvacStandardPartsEntity extends BaseEntity {


    /**
     * 部件名称
     */
    private String partsName;

    /**
     * 规格型号
     */
    private String specifications;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 字典id
     */
    private Long dictUnitId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 1自制标准件3外购标准件
     */
    private Integer type;

    /**
     * 单位名称
     */
    @TableField(exist = false)
    private String dictUnitName;


}
