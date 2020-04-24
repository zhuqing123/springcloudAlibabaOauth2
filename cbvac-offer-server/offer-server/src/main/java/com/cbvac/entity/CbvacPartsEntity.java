package com.cbvac.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cbvac.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 部件表
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cbvac_parts")
public class CbvacPartsEntity extends BaseEntity {

    /**
     * 生产类型
     */
    private Integer productType;

    /**
     * 标准品id
     */
    private Long standardId;

    /**
     * 部件名称
     */
    private String partsName;

    /**
     * 规格型号
     */
    private String specifications;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 单位
     */
    private String unit;

    /**
     * 数量
     */
    private Integer partsCount;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 系统id
     */
    private Long systemId;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 材料系数
     */
    private BigDecimal materialCoefficient;

    /**
     * 材料系数说明
     */
    private String materialCoefficientState;

    /**
     * 加工系数
     */
    private BigDecimal processCoefficient;

    /**
     * 加工系数说明
     */
    private String processCoefficientState;

    /**
     * 物料来源
     */
    private Integer materialSource;

    /**
     * 加工总价
     */
    private BigDecimal processTotalPrice;


}
