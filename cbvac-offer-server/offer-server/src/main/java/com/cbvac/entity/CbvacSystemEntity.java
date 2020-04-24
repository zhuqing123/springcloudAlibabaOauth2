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
 * 子系统
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cbvac_system")
public class CbvacSystemEntity extends BaseEntity {

    /**
     * 子系统名称
     */
    private String systemName;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 备注
     */
    private String remarks;


}
