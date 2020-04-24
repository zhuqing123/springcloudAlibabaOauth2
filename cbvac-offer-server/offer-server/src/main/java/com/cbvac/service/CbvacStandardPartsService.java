package com.cbvac.service;

import com.cbvac.dto.CbvacStandardPartsTemplateDto;
import com.cbvac.dto.StandardPartsDto;
import com.cbvac.dto.StandardPartsPageDto;
import com.cbvac.entity.CbvacStandardPartsEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ResultVo;
import com.cbvac.vo.StandardPartsVo;

import java.util.List;

/**
 * <p>
 * 自制标准件 服务类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
public interface CbvacStandardPartsService extends IService<CbvacStandardPartsEntity> {

    /**
     * 自制标准件模板导入
     *
     * @param dtos
     * @return
     */
    ResultVo uploadHomemadeStandard(List<CbvacStandardPartsTemplateDto> dtos);

    /**
     * 分页查询
     *
     * @param dto
     * @return
     */
    ResultVo<PageVo<StandardPartsVo>> findAllPage(StandardPartsPageDto dto);

    /**
     * 通过id查询自制标准件，外购标准件
     *
     * @param id
     * @return
     */
    StandardPartsVo findStandardPartsById(Long id);

    /**
     * 新增外购标准品与自制标准品
     *
     * @param dto
     * @return
     */
    ResultVo insetStandardParts(StandardPartsDto dto);

    /**
     * 编辑外购标准品与自制标准品
     * @param id
     * @param dto
     * @return
     */
    ResultVo editStandardParts(Long id, StandardPartsDto dto);
}
