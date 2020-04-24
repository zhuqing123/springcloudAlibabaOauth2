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
 * 材质密度表
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cbvac_material_density")
public class CbvacMaterialDensityEntity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 材质类型
     */
    private String materialType;

    /**
     * 材质名称
     */
    private String materialName;

    /**
     * 密度
     */
    private BigDecimal density;


}
