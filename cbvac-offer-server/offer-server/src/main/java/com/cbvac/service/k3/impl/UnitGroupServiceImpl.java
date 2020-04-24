package com.cbvac.service.k3.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbvac.entity.k3.UnitGroupEntity;
import com.cbvac.mapper.k3.UnitGroupMapper;
import com.cbvac.service.k3.UnitGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-15:36
 * @Description:
 */
@Service
public class UnitGroupServiceImpl extends ServiceImpl<UnitGroupMapper, UnitGroupEntity> implements UnitGroupService {

    public static final Logger LOGGER= LoggerFactory.getLogger(UnitGroupServiceImpl.class);

    @Autowired
    private UnitGroupMapper unitGroupMapper;

    @Override
    public List<UnitGroupEntity> findAll(){
        List<UnitGroupEntity> list = this.unitGroupMapper.selectList(Wrappers.emptyWrapper());
        return list;
    }
}
