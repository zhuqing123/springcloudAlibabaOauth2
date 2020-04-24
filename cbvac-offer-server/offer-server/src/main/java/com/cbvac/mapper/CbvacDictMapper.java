package com.cbvac.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbvac.dto.CbvacDictPageDto;
import com.cbvac.entity.CbvacDictEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典配置表 Mapper 接口
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-17
 */
@DS("master")
public interface CbvacDictMapper extends BaseMapper<CbvacDictEntity> {


    /**
     * 自定义插入
     *
     * @param sysDictEntity
     * @return
     */
    CbvacDictEntity selfInset(CbvacDictEntity sysDictEntity);

    /**
     * 自定查询计量单位
     *
     * @param page
     * @param dto
     * @return
     */
    IPage selectUnit(Page page, CbvacDictPageDto dto);

    /**
     * 通过批量父value去查询字典值
     * @param parentValues
     * @return
     */
    List<CbvacDictEntity> selectByParentValueIn(@Param("parentValues") List<String> parentValues);

}
