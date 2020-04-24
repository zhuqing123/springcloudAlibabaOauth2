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
 * @Date: 2020-04-17-16:01
 * @Description: 计量单位表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_MeasureUnit")
public class MeasureUnitEntity implements Serializable {

    @TableId(value = "FMeasureUnitID", type = IdType.INPUT)
    private Integer measureUnitID;

    @TableField(value = "FUnitGroupID")
    private Integer unitGroupID;

    @TableField(value = "FName")
    private String name;
}
