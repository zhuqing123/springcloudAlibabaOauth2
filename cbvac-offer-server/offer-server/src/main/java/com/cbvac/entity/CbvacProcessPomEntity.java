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
 * 
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cbvac_process_pom")
public class CbvacProcessPomEntity extends BaseEntity {

    /**
     * 工序id 
     */
    private Long processId;

    /**
     * 工序名称
     */
    private String processName;

    /**
     * 数量
     */
    private BigDecimal processCount;

    /**
     * 单位
     */
    private String unitName;

    /**
     * 单价
     */
    private BigDecimal processPrice;

    /**
     * 总价
     */
    private BigDecimal totalProcessPrice;

    /**
     * 部件id
     */
    private Long partsId;


}
