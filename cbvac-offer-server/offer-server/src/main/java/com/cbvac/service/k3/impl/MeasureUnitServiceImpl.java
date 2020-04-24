package com.cbvac.service.k3.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbvac.entity.k3.MeasureUnitEntity;
import com.cbvac.mapper.k3.MeasureUnitMapper;
import com.cbvac.service.k3.MeasureUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-16:11
 * @Description:
 */
@Service
public class MeasureUnitServiceImpl extends ServiceImpl<MeasureUnitMapper, MeasureUnitEntity> implements MeasureUnitService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MeasureUnitServiceImpl.class);


    @Override
    public List<MeasureUnitEntity> findByGroupId(Integer groupId) {
        MeasureUnitEntity measureUnitEntity = new MeasureUnitEntity();
        measureUnitEntity.setUnitGroupID(groupId);

        List<MeasureUnitEntity> list = super.list(new QueryWrapper<MeasureUnitEntity>().lambda().eq(MeasureUnitEntity::getUnitGroupID, groupId));
        return list;
    }

    @Override
    public List<MeasureUnitEntity> findAll() {
        return super.list();
    }
}
