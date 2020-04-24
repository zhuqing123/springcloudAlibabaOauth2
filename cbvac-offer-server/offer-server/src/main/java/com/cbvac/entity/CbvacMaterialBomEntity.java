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
 * 部件对应的材质类型表
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cbvac_material_bom")
public class CbvacMaterialBomEntity extends BaseEntity {

    /**
     * 材质类型id
     */
    private Long materialId;

    /**
     * 材质类型
     */
    private String materialType;

    /**
     * 材质名称
     */
    private String materialName;

    /**
     * 材质规格
     */
    private String materialSpecifications;

    /**
     * 材质单价
     */
    private BigDecimal materialPrice;

    /**
     * 密度
     */
    private BigDecimal density;

    /**
     * 原材料类型value
     */
    private String rawMaterialValue;

    /**
     * 原材料名称
     */
    private String rawMaterialName;

    /**
     * 长
     */
    private Integer length;

    /**
     * 宽
     */
    private Integer wide;

    /**
     * 高
     */
    private Integer hight;

    /**
     * 外径
     */
    private Integer outerDiameter;

    /**
     * 内径
     */
    private Integer innerDiameter;

    /**
     * 部件id
     */
    private Long partsId;


}
