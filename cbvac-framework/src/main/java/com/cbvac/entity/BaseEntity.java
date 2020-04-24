package com.cbvac.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-13:54
 * @Description:
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建人id
     */
    private Long createId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人id
     */
    private Long modifyId;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date modifyTime;

    /**
     * 删除标识1删除默认0
     */
    @TableLogic(value = "0",delval = "1")
    private Integer delFlag;
}
