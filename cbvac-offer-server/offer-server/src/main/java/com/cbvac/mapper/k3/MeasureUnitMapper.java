package com.cbvac.mapper.k3;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cbvac.entity.k3.MeasureUnitEntity;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-16:08
 * @Description:
 */
@DS("slave_1")
public interface MeasureUnitMapper extends BaseMapper<MeasureUnitEntity> {
}
