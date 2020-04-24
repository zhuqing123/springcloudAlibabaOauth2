package com.cbvac.service;

import com.cbvac.dto.CbvacMaterialDensityTemplateDto;
import com.cbvac.dto.MaterialDensityDto;
import com.cbvac.dto.MaterialDensityPageDto;
import com.cbvac.entity.CbvacMaterialDensityEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cbvac.vo.MaterialDensityPageVo;
import com.cbvac.vo.ResultVo;

import java.util.List;

/**
 * <p>
 * 材质密度表 服务类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-20
 */
public interface CbvacMaterialDensityService extends IService<CbvacMaterialDensityEntity> {

    /**
     * 保存导入进来的数据
     * @param dtos
     * @return
     */
    ResultVo uploadMaterialDensity(List<CbvacMaterialDensityTemplateDto> dtos);

    /**
     * 通过材质类型与名称查询材质密度
     * @param materialType
     * @param materialName
     * @return
     */
     CbvacMaterialDensityEntity findMaterialDensityByNameAndType(String materialType, String materialName);

    /**
     * 材质密度列表
     * @param dto
     * @return
     */
    ResultVo findAllPage(MaterialDensityPageDto dto);

    /**
     * 新增材质密度
     * @param dto
     * @return
     */
    ResultVo insertMaterialDensity(MaterialDensityDto dto);

    /**
     * 编辑回显
     * @param id
     * @return
     */
    MaterialDensityPageVo findById(Long id);

    /**
     * 编辑
     * @param id
     * @param dto
     * @return
     */
    ResultVo editMaterialDensity(Long id, MaterialDensityDto dto);

    /**
     * 查询所有
     * @return
     */
    ResultVo findAllNoPage();
}
