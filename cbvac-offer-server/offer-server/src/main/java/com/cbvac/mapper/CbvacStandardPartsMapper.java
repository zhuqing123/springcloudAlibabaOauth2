package com.cbvac.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbvac.dto.StandardPartsPageDto;
import com.cbvac.entity.CbvacStandardPartsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 自制标准件 Mapper 接口
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
public interface CbvacStandardPartsMapper extends BaseMapper<CbvacStandardPartsEntity> {

    IPage<CbvacStandardPartsEntity> selfSelectPage(Page<CbvacStandardPartsEntity> page, StandardPartsPageDto dto);

    CbvacStandardPartsEntity selfSelectById(@Param("id") Long id);
}