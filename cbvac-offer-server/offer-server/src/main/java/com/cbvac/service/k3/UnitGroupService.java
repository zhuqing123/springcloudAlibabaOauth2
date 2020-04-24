package com.cbvac.service.k3;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cbvac.entity.k3.UnitGroupEntity;

import java.util.List;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-15:35
 * @Description:
 */
public interface UnitGroupService extends IService<UnitGroupEntity> {

     List<UnitGroupEntity> findAll();
}
