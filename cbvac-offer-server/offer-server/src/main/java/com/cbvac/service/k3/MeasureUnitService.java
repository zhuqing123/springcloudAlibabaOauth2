package com.cbvac.service.k3;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cbvac.entity.k3.MeasureUnitEntity;

import java.util.List;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-16:10
 * @Description:
 */
public interface MeasureUnitService extends IService<MeasureUnitEntity> {

    List<MeasureUnitEntity>  findByGroupId(Integer groupId);

    List<MeasureUnitEntity> findAll();
}
