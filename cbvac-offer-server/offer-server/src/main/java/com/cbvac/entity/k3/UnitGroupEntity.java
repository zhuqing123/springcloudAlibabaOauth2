package com.cbvac.entity.k3;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-15:19
 * @Description: k3计量单位分组
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_UnitGroup")
public class UnitGroupEntity implements Serializable {

    @TableId(value = "FUnitGroupID", type = IdType.INPUT)
    private Integer unitGroupID;

    @TableField(value = "FName")
    private String name;

    @TableField(value = "FDefaultUnitID")
    private Integer defaultUnitID;

    @TableField(value = "FModifyTime")
    private Byte[] modifyTime;

    @TableField(value = "UUID")
    private String uuId;
}
