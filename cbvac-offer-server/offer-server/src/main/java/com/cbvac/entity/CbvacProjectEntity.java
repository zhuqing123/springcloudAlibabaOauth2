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
 * 项目表
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cbvac_project")
public class CbvacProjectEntity extends BaseEntity {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目类型0系统集成1定制品
     */
    private Integer projectType;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 包装费
     */
    private BigDecimal packing;

    /**
     * 运输费
     */
    private BigDecimal transportation;

    /**
     * 材料费
     */
    private BigDecimal materialsPrice;

    /**
     * 备注
     */
    private String remarks;


}
