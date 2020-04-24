package com.cbvac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbvac.dto.CbvacMaterialDensityTemplateDto;
import com.cbvac.dto.MaterialDensityDto;
import com.cbvac.dto.MaterialDensityPageDto;
import com.cbvac.entity.CbvacMaterialDensityEntity;
import com.cbvac.enums.ResultEnum;
import com.cbvac.mapper.CbvacMaterialDensityMapper;
import com.cbvac.service.CbvacMaterialDensityService;
import com.cbvac.utils.PageUtil;
import com.cbvac.vo.MaterialDensityPageVo;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 材质密度表 服务实现类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-20
 */
@Service
public class CbvacMaterialDensityServiceImpl extends ServiceImpl<CbvacMaterialDensityMapper, CbvacMaterialDensityEntity> implements CbvacMaterialDensityService {

    @Transactional
    @Override
    public ResultVo uploadMaterialDensity(List<CbvacMaterialDensityTemplateDto> dtos) {
        if (!CollectionUtils.isEmpty(dtos)) {
            CbvacMaterialDensityEntity materialDensityEntity = null;
            List<CbvacMaterialDensityEntity> materialDensityEntities = new ArrayList<>();

            //组装查询条件，根据类型与名称筛选出已经存在的
            for (CbvacMaterialDensityTemplateDto dto : dtos) {
                materialDensityEntity = this.findMaterialDensityByNameAndType(dto.getMaterialType(), dto.getMaterialName());
                if (null != materialDensityEntity) {
                    continue;
                }
                materialDensityEntity = new CbvacMaterialDensityEntity();
                BeanUtils.copyProperties(dto, materialDensityEntity);
                materialDensityEntities.add(materialDensityEntity);
            }
            if (!CollectionUtils.isEmpty(materialDensityEntities)) {
                //  super.saveBatch(materialDensityEntities);
            }
        }
        return new ResultVo(ResultEnum.SUCCESS);
    }

    @Override
    public CbvacMaterialDensityEntity findMaterialDensityByNameAndType(String materialType, String materialName) {
        QueryWrapper<CbvacMaterialDensityEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(materialType)) {
            queryWrapper.lambda().eq(CbvacMaterialDensityEntity::getMaterialType, materialType);
        } else {
            queryWrapper.lambda().isNull(CbvacMaterialDensityEntity::getMaterialType);
        }

        if (StringUtils.isNoneBlank(materialName)) {
            queryWrapper.lambda().eq(CbvacMaterialDensityEntity::getMaterialName, materialName);
        } else {
            queryWrapper.lambda().isNull(CbvacMaterialDensityEntity::getMaterialName);
        }

        return super.getOne(queryWrapper);
    }

    @Override
    public ResultVo findAllPage(MaterialDensityPageDto dto) {
        QueryWrapper<CbvacMaterialDensityEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(dto.getMaterialName())) {
            queryWrapper.lambda().eq(CbvacMaterialDensityEntity::getMaterialName, dto.getMaterialName());
        }
        if (StringUtils.isNoneBlank(dto.getMaterialType())) {
            queryWrapper.lambda().eq(CbvacMaterialDensityEntity::getMaterialType, dto.getMaterialType());
        }
        Page page = super.page(dto.getPage(), queryWrapper);
        PageVo pageVo = PageUtil.pageToPageVo(page, MaterialDensityPageVo.class);
        return new ResultVo(ResultEnum.SUCCESS, pageVo);
    }

    @Transactional
    @Override
    public ResultVo insertMaterialDensity(MaterialDensityDto dto) {
        CbvacMaterialDensityEntity materialDensityEntity = new CbvacMaterialDensityEntity();
        BeanUtils.copyProperties(dto, materialDensityEntity);
        super.save(materialDensityEntity);
        return ResultVo.success();
    }

    @Override
    public MaterialDensityPageVo findById(Long id) {
        CbvacMaterialDensityEntity materialDensityEntity = super.getById(id);
        MaterialDensityPageVo vo = null;
        if (null != materialDensityEntity) {
            vo = new MaterialDensityPageVo();
            BeanUtils.copyProperties(materialDensityEntity, vo);
        }
        return vo;
    }

    @Transactional
    @Override
    public ResultVo editMaterialDensity(Long id, MaterialDensityDto dto) {
        CbvacMaterialDensityEntity materialDensityEntity = super.getById(id);
        if (null == materialDensityEntity) {
            return new ResultVo(ResultEnum.MATERIAL_DENSITY_NOT_FOUND);
        }
        BeanUtils.copyProperties(dto, materialDensityEntity);
        super.saveOrUpdate(materialDensityEntity);
        return ResultVo.success();
    }

    @Override
    public ResultVo findAllNoPage() {
        List<CbvacMaterialDensityEntity> list = super.list();
        List<MaterialDensityPageVo> vos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            MaterialDensityPageVo vo = null;
            for (CbvacMaterialDensityEntity materialDensityEntity : list) {
                vo = new MaterialDensityPageVo();
                BeanUtils.copyProperties(materialDensityEntity, vo);
                vos.add(vo);
            }
        }
        return new ResultVo(ResultEnum.SUCCESS, vos);
    }


}
