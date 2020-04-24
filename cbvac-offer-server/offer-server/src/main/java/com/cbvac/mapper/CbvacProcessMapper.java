package com.cbvac.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbvac.dto.ProcessPageDto;
import com.cbvac.entity.CbvacProcessEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 工序配置 Mapper 接口
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
public interface CbvacProcessMapper extends BaseMapper<CbvacProcessEntity> {

    /**
     * 自定义分页查询
     *
     * @param page
     * @param dto
    selfSelectPageList     * @return
     */
    IPage<CbvacProcessEntity> selfSelectPageList(Page page,ProcessPageDto dto);

    /**
     * 通过id查询工序
     * @param id
     * @return
     */
    CbvacProcessEntity selfSelectById(@Param("id") Long id);

}
