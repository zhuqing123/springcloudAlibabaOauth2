package com.cbvac.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbvac.dto.MaterialTypePageDto;
import com.cbvac.entity.CbvacMaterialTypeEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 材质类型表 Mapper 接口
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-20
 */
public interface CbvacMaterialTypeMapper extends BaseMapper<CbvacMaterialTypeEntity> {

    IPage<CbvacMaterialTypeEntity> findAllPage(Page<CbvacMaterialTypeEntity> page,MaterialTypePageDto dto);

    CbvacMaterialTypeEntity findSelfById(Long id);

}
