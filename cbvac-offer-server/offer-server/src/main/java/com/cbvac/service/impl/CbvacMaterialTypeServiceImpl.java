package com.cbvac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cbvac.dto.CbvacMaterialTypeTemplateDto;
import com.cbvac.dto.MaterialTypeDto;
import com.cbvac.dto.MaterialTypePageDto;
import com.cbvac.entity.CbvacMaterialDensityEntity;
import com.cbvac.entity.CbvacMaterialTypeEntity;
import com.cbvac.entity.CbvacDictEntity;
import com.cbvac.enums.DictValueEnum;
import com.cbvac.enums.ResultEnum;
import com.cbvac.mapper.CbvacMaterialTypeMapper;
import com.cbvac.service.CbvacMaterialDensityService;
import com.cbvac.service.CbvacMaterialTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbvac.service.CbvacDictService;
import com.cbvac.utils.PageUtil;
import com.cbvac.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 材质类型表 服务实现类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-20
 */
@Service
public class CbvacMaterialTypeServiceImpl extends ServiceImpl<CbvacMaterialTypeMapper, CbvacMaterialTypeEntity> implements CbvacMaterialTypeService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacMaterialTypeServiceImpl.class);

    @Autowired
    private CbvacDictService sysDictService;

    @Autowired
    private CbvacMaterialDensityService materialDensityService;

    @Transactional
    @Override
    public ResultVo uploadMaterialType(List<CbvacMaterialTypeTemplateDto> dtos) {
        if (!CollectionUtils.isEmpty(dtos)) {
            CbvacDictEntity sysDictEntity = null;
            CbvacMaterialTypeEntity materialTypeEntity = null;
            CbvacMaterialDensityEntity materialDensityEntity = null;
            List<CbvacMaterialTypeEntity> list = new ArrayList<>();
            for (CbvacMaterialTypeTemplateDto dto : dtos) {

                if (StringUtils.isBlank(dto.getSpecifications())) {
                    continue;
                }
                materialDensityEntity = materialDensityService.findMaterialDensityByNameAndType(dto.getMaterialType(), dto.getMaterialName());
                if (null == materialDensityEntity) {
                    continue;
                }

                materialTypeEntity = super.getOne(new QueryWrapper<CbvacMaterialTypeEntity>().lambda().eq(CbvacMaterialTypeEntity::getDensityId, materialDensityEntity.getId()).eq(CbvacMaterialTypeEntity::getSpecifications, dto.getSpecifications()));
                if (null != materialTypeEntity) {
                    continue;
                }

                materialTypeEntity = new CbvacMaterialTypeEntity();
                BeanUtils.copyProperties(dto, materialTypeEntity);
                materialTypeEntity.setDensityId(materialDensityEntity.getId());

                sysDictEntity = this.sysDictService.findByDictName(dto.getDictName());
                if (null != sysDictEntity) {
                    materialTypeEntity.setDictUnitId(sysDictEntity.getId());
                }
                list.add(materialTypeEntity);
            }
            if (!CollectionUtils.isEmpty(list)) {
                super.saveBatch(list);
            }

        }
        return ResultVo.success();
    }

    @Override
    public ResultVo<PageVo<MaterialTypePageVo>> findAllPage(MaterialTypePageDto dto) {
        IPage page = super.baseMapper.findAllPage(dto.getPage(), dto);
        PageVo<MaterialTypePageVo> pageVo = PageUtil.pageToPageVo(page, MaterialTypePageVo.class);
        List<MaterialTypePageVo> records = pageVo.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            records
                    .stream()
                    .forEach(record -> {
                        if (record.getStatus().equals(DictValueEnum.getCodeValue(DictValueEnum.ENABLE))) {
                            record.setStatusValue(DictValueEnum.ENABLE.getDescribe());
                            record.setStatusBtnValue(DictValueEnum.DISABLE.getDescribe());
                        } else {
                            record.setStatusValue(DictValueEnum.DISABLE.getDescribe());
                            record.setStatusBtnValue(DictValueEnum.ENABLE.getDescribe());
                        }
                    });
        }
        return new ResultVo<>(ResultEnum.SUCCESS, pageVo);
    }

    @Transactional
    @Override
    public ResultVo insertMaterialType(MaterialTypeDto dto) {
        ResultVo resultVo = this.validataMaterialParam(dto, null);
        if (resultVo != null) {
            return resultVo;
        }
        CbvacMaterialTypeEntity materialTypeEntity = new CbvacMaterialTypeEntity();
        BeanUtils.copyProperties(dto, materialTypeEntity);
        super.save(materialTypeEntity);
        return ResultVo.success();
    }

    @Override
    public MaterialTypePageVo findById(Long id) {
        CbvacMaterialTypeEntity materialTypeEntity = super.baseMapper.findSelfById(id);
        MaterialTypePageVo vo = null;
        if (null != materialTypeEntity) {
            vo = new MaterialTypePageVo();
            BeanUtils.copyProperties(materialTypeEntity, vo);
        }
        return vo;
    }

    @Transactional
    @Override
    public ResultVo editMaterialType(Long id, MaterialTypeDto dto) {
        ResultVo resultVo = validataMaterialParam(dto, id);
        if (resultVo != null) {
            return resultVo;
        }

        CbvacMaterialTypeEntity materialTypeEntity = super.getById(id);
        if (null == materialTypeEntity) {
            return new ResultVo(ResultEnum.MATERIAL_TYPE_NOT_FOUND);
        }
        BeanUtils.copyProperties(dto, materialTypeEntity);
        super.updateById(materialTypeEntity);
        return ResultVo.success();
    }

    @Override
    public ResultVo editMaterialTypeStatus(Long id) {
        CbvacMaterialTypeEntity materialTypeEntity = super.getById(id);
        if (null == materialTypeEntity) {
            return new ResultVo(ResultEnum.MATERIAL_TYPE_NOT_FOUND);
        }
        if (materialTypeEntity.getStatus().equals(DictValueEnum.ENABLE.getCodeToInt())) {
            materialTypeEntity.setStatus(DictValueEnum.DISABLE.getCodeToInt());
        } else {
            materialTypeEntity.setStatus(DictValueEnum.ENABLE.getCodeToInt());
        }
        super.updateById(materialTypeEntity);
        return ResultVo.success();
    }

    /**
     * 参数验证
     *
     * @param dto
     * @return
     */
    private ResultVo validataMaterialParam(MaterialTypeDto dto, Long id) {
        MaterialDensityPageVo materialDensityPageVo = this.materialDensityService.findById(dto.getDensityId());
        if (null == materialDensityPageVo) {
            return new ResultVo(ResultEnum.MATERIAL_DENSITY_NOT_FOUND);
        }
        CbvacDictPageVo dictVo = this.sysDictService.findDictById(dto.getDictUnitId());
        if (null == dictVo) {
            return new ResultVo(ResultEnum.DICT_NOT_FOUND);
        }
        CbvacMaterialTypeEntity materialTypeEntity = super.getOne(
                new QueryWrapper<CbvacMaterialTypeEntity>()
                        .lambda()
                        .eq(CbvacMaterialTypeEntity::getDensityId, dto.getDensityId())
                        .eq(CbvacMaterialTypeEntity::getSpecifications, dto.getSpecifications())
        );

        if (null != materialTypeEntity) {
            if (id != null && !id.equals(materialTypeEntity.getId())) {
                return new ResultVo(ResultEnum.MATERIAL_TYPE_EXIST);
            } else if (null == id) {
                return new ResultVo(ResultEnum.MATERIAL_TYPE_EXIST);
            }
        }
        return null;
    }
}
