package com.cbvac.service.impl;

import com.alibaba.druid.sql.PagerUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cbvac.dto.CbvacStandardPartsTemplateDto;
import com.cbvac.dto.StandardPartsDto;
import com.cbvac.dto.StandardPartsOutDto;
import com.cbvac.dto.StandardPartsPageDto;
import com.cbvac.entity.CbvacDictEntity;
import com.cbvac.entity.CbvacStandardPartsEntity;
import com.cbvac.enums.DictValueEnum;
import com.cbvac.enums.ResultEnum;
import com.cbvac.mapper.CbvacStandardPartsMapper;
import com.cbvac.service.CbvacDictService;
import com.cbvac.service.CbvacStandardPartsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbvac.utils.PageUtil;
import com.cbvac.vo.CbvacDictPageVo;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ResultVo;
import com.cbvac.vo.StandardPartsVo;
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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 自制标准件 服务实现类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
@Service
public class CbvacStandardPartsServiceImpl extends ServiceImpl<CbvacStandardPartsMapper, CbvacStandardPartsEntity> implements CbvacStandardPartsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacStandardPartsServiceImpl.class);

    @Autowired
    private CbvacDictService cbvacDictService;

    @Override
    public ResultVo uploadHomemadeStandard(List<CbvacStandardPartsTemplateDto> dtos) {
        if (!CollectionUtils.isEmpty(dtos)) {
            List<String> dictNames = dtos.stream().map(CbvacStandardPartsTemplateDto::getDictUnitName).distinct().collect(Collectors.toList());
            List<CbvacDictEntity> cbvacDictEntityList = this.cbvacDictService.findByDictNameIn(dictNames);
            CbvacStandardPartsEntity cbvacHomemadeStandardEntity = null;
            Optional<CbvacDictEntity> optional = null;
            List<CbvacStandardPartsEntity> cbvacHomemadeStandardEntities = new ArrayList<>();
            QueryWrapper<CbvacStandardPartsEntity> queryWrapper = null;
            for (CbvacStandardPartsTemplateDto dto : dtos) {
                queryWrapper = new QueryWrapper<>();
                if (StringUtils.isNoneBlank(dto.getPartsName())) {
                    queryWrapper
                            .lambda()
                            .eq(CbvacStandardPartsEntity::getPartsName, dto.getPartsName());
                } else {
                    queryWrapper
                            .lambda()
                            .isNull(CbvacStandardPartsEntity::getPartsName);
                }

                if (StringUtils.isNoneBlank(dto.getSpecifications())) {
                    queryWrapper
                            .lambda()
                            .eq(CbvacStandardPartsEntity::getSpecifications, dto.getSpecifications());
                } else {
                    queryWrapper
                            .lambda()
                            .isNull(CbvacStandardPartsEntity::getSpecifications);
                }
                cbvacHomemadeStandardEntity = super.getOne(queryWrapper);
                if (null != cbvacHomemadeStandardEntity) {
                    continue;
                }

                cbvacHomemadeStandardEntity = new CbvacStandardPartsEntity();
                BeanUtils.copyProperties(dto, cbvacHomemadeStandardEntity);
                if (!CollectionUtils.isEmpty(cbvacDictEntityList)) {
                    optional = cbvacDictEntityList.stream().filter(cbvacDictEntity -> StringUtils.equals(dto.getDictUnitName(), cbvacDictEntity.getDictName())).findFirst();
                    if (optional.isPresent()) {
                        cbvacHomemadeStandardEntity.setDictUnitId(optional.get().getId());
                    }
                }
                cbvacHomemadeStandardEntities.add(cbvacHomemadeStandardEntity);
            }

            if (!CollectionUtils.isEmpty(cbvacHomemadeStandardEntities)) {
                super.saveBatch(cbvacHomemadeStandardEntities);
            }
        }

        return ResultVo.success();
    }

    @Override
    public ResultVo<PageVo<StandardPartsVo>> findAllPage(StandardPartsPageDto dto) {
        IPage iPage = super.baseMapper.selfSelectPage(dto.getPage(), dto);
        PageVo<StandardPartsVo> pageVo = PageUtil.pageToPageVo(iPage, StandardPartsVo.class);
        return new ResultVo<>(ResultEnum.SUCCESS, pageVo);
    }

    @Override
    public StandardPartsVo findStandardPartsById(Long id) {
        CbvacStandardPartsEntity standardPartsEntity = super.baseMapper.selfSelectById(id);
        StandardPartsVo vo = null;
        if (null != standardPartsEntity) {
            vo = new StandardPartsVo();
            BeanUtils.copyProperties(standardPartsEntity, vo);
        }
        return vo;
    }

    @Transactional
    @Override
    public ResultVo insetStandardParts(StandardPartsDto dto) {

        ResultVo resultVo = this.validataStandardParts(dto, null);
        if (resultVo != null) {
            return resultVo;
        }
        CbvacStandardPartsEntity standardPartsEntity = new CbvacStandardPartsEntity();
        BeanUtils.copyProperties(dto, standardPartsEntity);
        super.save(standardPartsEntity);
        return ResultVo.success();
    }


    @Transactional
    @Override
    public ResultVo editStandardParts(Long id, StandardPartsDto dto) {
        ResultVo resultVo = this.validataStandardParts(dto, null);
        if (resultVo != null) {
            return resultVo;
        }
        CbvacStandardPartsEntity standardPartsEntity = super.getById(id);
        if (null == standardPartsEntity) {
            return new ResultVo(ResultEnum.DATA_NOT_FOUND);
        }
        BeanUtils.copyProperties(dto, standardPartsEntity);
        super.updateById(standardPartsEntity);
        return ResultVo.success();
    }

    /**
     * 参数验证
     *
     * @param dto
     * @return
     */
    private ResultVo validataStandardParts(StandardPartsDto dto, Long id) {
        CbvacDictPageVo dictPageVo = this.cbvacDictService.findDictById(dto.getDictUnitId());
        if (null == dictPageVo) {
            return new ResultVo(ResultEnum.DICT_NOT_FOUND);
        }
        QueryWrapper<CbvacStandardPartsEntity> queryWrapper = new QueryWrapper<>();
        if (dto.getType().equals(DictValueEnum.OUTSOURCE_STANDARD_PARTS.getCodeToInt())) {
            StandardPartsOutDto standardPartsOutDto = (StandardPartsOutDto) dto;
            queryWrapper.lambda().eq(CbvacStandardPartsEntity::getBrand, standardPartsOutDto.getBrand());

        }
        CbvacStandardPartsEntity standardPartsEntity = super.getOne(
                queryWrapper
                        .lambda()
                        .eq(CbvacStandardPartsEntity::getPartsName, dto.getPartsName())
                        .eq(CbvacStandardPartsEntity::getSpecifications, dto.getSpecifications())
                        .eq(CbvacStandardPartsEntity::getType, dto.getType())
        );
        if (null != standardPartsEntity) {
            if (null != id && !id.equals(standardPartsEntity.getId())) {
                return new ResultVo(ResultEnum.STANDARD_PARTS_EXIST);
            } else if (null == id) {
                return new ResultVo(ResultEnum.STANDARD_PARTS_EXIST);
            }
        }
        return null;
    }
}
