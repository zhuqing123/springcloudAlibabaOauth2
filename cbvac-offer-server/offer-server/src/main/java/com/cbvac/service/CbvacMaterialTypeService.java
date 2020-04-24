package com.cbvac.service;

import com.cbvac.dto.CbvacMaterialTypeTemplateDto;
import com.cbvac.dto.MaterialTypeDto;
import com.cbvac.dto.MaterialTypePageDto;
import com.cbvac.entity.CbvacMaterialTypeEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cbvac.vo.MaterialTypePageVo;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ResultVo;

import java.util.List;

/**
 * <p>
 * 材质类型表 服务类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-20
 */
public interface CbvacMaterialTypeService extends IService<CbvacMaterialTypeEntity> {

    /**
     * 导入材质类型处理类
     *
     * @param dtos
     * @return
     */
    ResultVo uploadMaterialType(List<CbvacMaterialTypeTemplateDto> dtos);

    /**
     * 分页查询所有材质类型
     *
     * @param dto
     * @return
     */
    ResultVo<PageVo<MaterialTypePageVo>> findAllPage(MaterialTypePageDto dto);

    /**
     * 添加材质类型
     * @param dto
     * @return
     */
    ResultVo insertMaterialType(MaterialTypeDto dto);

    /**
     * 通过id查询材质类型
     * @param id
     * @return
     */
    MaterialTypePageVo findById(Long id);

    /**
     * 编辑材质类型
     * @param id
     * @param dto
     * @return
     */
    ResultVo editMaterialType(Long id, MaterialTypeDto dto);

    /**
     * 启用停用
     * @param id
     * @return
     */
    ResultVo editMaterialTypeStatus(Long id);
}
