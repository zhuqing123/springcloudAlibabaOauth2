package com.cbvac.entity;


import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典配置表
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cbvac_dict")
public class CbvacDictEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典name
     */
    private String dictName;

    /**
     * 字典value
     */
    private String dictValue;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 父value
     */
    private String parentValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 0停用1启用
     */
    private Integer status;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 父名称
     */
    @TableField(exist = false)
    private String parentName;


}
