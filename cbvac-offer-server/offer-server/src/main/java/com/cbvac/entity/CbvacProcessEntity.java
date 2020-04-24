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
 * 工序配置
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cbvac_process")
public class CbvacProcessEntity extends BaseEntity {

    /**
     * 工序名称
     */
    private String processName;

    /**
     * 工序单价
     */
    private BigDecimal processPrice;

    /**
     * 单位id
     */
    private Long dictUnitId;

    /**
     * 启停状态0停用1启用
     */
    private Integer status;

    /**
     * 单位名称
     */
    @TableField(exist = false)
    private String dictUnitName;


}
