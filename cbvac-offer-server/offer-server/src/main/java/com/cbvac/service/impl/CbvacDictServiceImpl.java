package com.cbvac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbvac.dto.CbvacDictDto;
import com.cbvac.dto.CbvacDictEditDto;
import com.cbvac.dto.CbvacDictPageDto;
import com.cbvac.dto.UnitTypeDto;
import com.cbvac.entity.CbvacDictEntity;
import com.cbvac.entity.k3.MeasureUnitEntity;
import com.cbvac.entity.k3.UnitGroupEntity;
import com.cbvac.enums.DictTypeEnum;
import com.cbvac.enums.DictValueEnum;
import com.cbvac.enums.ResultEnum;
import com.cbvac.mapper.CbvacDictMapper;
import com.cbvac.service.CbvacDictService;
import com.cbvac.service.k3.MeasureUnitService;
import com.cbvac.service.k3.UnitGroupService;
import com.cbvac.utils.PageUtil;
import com.cbvac.vo.CbvacDictPageVo;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典配置表 服务实现类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-17
 */
@Service
public class CbvacDictServiceImpl extends ServiceImpl<CbvacDictMapper, CbvacDictEntity> implements CbvacDictService {

    @Autowired
    private UnitGroupService unitGroupService;
    @Autowired
    private MeasureUnitService measureUnitService;


    @Override
    public Boolean synchronousK3DictType() {
        List<UnitGroupEntity> unitGroupEntities = this.unitGroupService.findAll();

        if (!CollectionUtils.isEmpty(unitGroupEntities)) {
            int i = 0;
            CbvacDictEntity sysDictEntity = null;
            List<MeasureUnitEntity> measureUnitEntities = null;
            for (UnitGroupEntity unitGroupEntity : unitGroupEntities) {

                if (0 != unitGroupEntity.getDefaultUnitID()) {
                    sysDictEntity = new CbvacDictEntity();
                    if (null != DictTypeEnum.findByType(String.valueOf(unitGroupEntity.getUnitGroupID()))) {
                        sysDictEntity.setDictName(unitGroupEntity.getName());
                        sysDictEntity.setDictValue(String.valueOf(unitGroupEntity.getUnitGroupID()));
                        sysDictEntity.setSort(i);
                        baseMapper.insert(sysDictEntity);
                        i = i + 10;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Boolean synchronousK3Dict() {
        List<MeasureUnitEntity> measureUnitEntities = this.measureUnitService.findAll();
        Map<Integer, List<MeasureUnitEntity>> map = measureUnitEntities
                .stream()
                .filter(measureUnitEntity -> !measureUnitEntity.getUnitGroupID().equals(0))
                .collect(Collectors.groupingBy(MeasureUnitEntity::getUnitGroupID));
        CbvacDictEntity parentSysDict = null;
        List<MeasureUnitEntity> measureUnitEntityList = null;
        int i = 0;
        List<CbvacDictEntity> sysDictEntities = new ArrayList<>();
        CbvacDictEntity sysDictEntity = null;
        for (Map.Entry<Integer, List<MeasureUnitEntity>> entry : map.entrySet()) {
            if (null == DictTypeEnum.findByType(entry.getKey().toString())) {
                parentSysDict = super.baseMapper.selectOne(new QueryWrapper<CbvacDictEntity>().lambda().eq(CbvacDictEntity::getDictValue, DictTypeEnum.WEIGHT_UNIT.getType()));
            } else {
                parentSysDict = super.baseMapper.selectOne(new QueryWrapper<CbvacDictEntity>().lambda().eq(CbvacDictEntity::getDictValue, entry.getKey().toString()));
            }
            measureUnitEntityList = entry.getValue();
            if (null != parentSysDict && !CollectionUtils.isEmpty(measureUnitEntityList)) {
                for (MeasureUnitEntity measureUnitEntity : measureUnitEntityList) {
                    sysDictEntity = new CbvacDictEntity();
                    sysDictEntity.setDictName(measureUnitEntity.getName());
                    sysDictEntity.setDictValue(measureUnitEntity.getMeasureUnitID().toString());
                    sysDictEntity.setSort(i);
                    sysDictEntity.setParentId(parentSysDict.getId());
                    sysDictEntity.setParentValue(parentSysDict.getDictValue());
                    sysDictEntities.add(sysDictEntity);
                    i++;
                }
            }
        }
        if (!CollectionUtils.isEmpty(sysDictEntities)) {
            super.saveBatch(sysDictEntities);
        }
        return true;
    }

    @Override
    public CbvacDictEntity findByDictName(String dictName) {
        return super.getOne(new QueryWrapper<CbvacDictEntity>().lambda().eq(CbvacDictEntity::getDictName, dictName));
    }

    @Transactional
    @Override
    public ResultVo insertSysDict(String parentValue, CbvacDictDto dto) {

        //验证传入参数合法性
        CbvacDictEntity parentEntity = this.getOne(new QueryWrapper<CbvacDictEntity>().lambda().eq(CbvacDictEntity::getDictValue, parentValue).isNull(CbvacDictEntity::getParentId));
        if (null == parentEntity) {
            return new ResultVo(ResultEnum.DICT_TYPE_NOT_FOUND);
        }
        if (this.vlidataDictValue(dto.getDictValue(), parentEntity.getId())) {
            return new ResultVo(ResultEnum.DICT_VALUE_REPEAT);
        }
        CbvacDictEntity sysDictEntity = new CbvacDictEntity();
        BeanUtils.copyProperties(dto, sysDictEntity);
        sysDictEntity.setParentId(parentEntity.getId());
        sysDictEntity.setParentValue(parentEntity.getDictValue());
        super.save(sysDictEntity);

        return ResultVo.success();
    }

    @Override
    public ResultVo insertSysDictType(CbvacDictDto dto) {
        if (this.vlidataDictValue(dto.getDictValue(), null)) {
            return new ResultVo(ResultEnum.DICT_VALUE_REPEAT);
        }
        CbvacDictEntity sysDictEntity;
        sysDictEntity = new CbvacDictEntity();
        BeanUtils.copyProperties(dto, sysDictEntity);
        super.save(sysDictEntity);
        return ResultVo.success();
    }

    @Override
    public List<CbvacDictEntity> findByDictNameIn(List<String> dictUnitNames) {
        List<CbvacDictEntity> cbvacDictEntityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dictUnitNames)) {
            cbvacDictEntityList = super.list(new QueryWrapper<CbvacDictEntity>().lambda().in(CbvacDictEntity::getDictName, dictUnitNames));
        }
        return cbvacDictEntityList;
    }

    @Override
    public ResultVo findDictByParentTypeAllPage(CbvacDictPageDto dto) {

        QueryWrapper<CbvacDictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CbvacDictEntity::getParentValue, dto.getParentValue());
        if (StringUtils.isNoneBlank(dto.getDictName())) {
            queryWrapper.lambda().like(CbvacDictEntity::getDictName, dto.getDictName());
        }
        if (StringUtils.isNoneBlank(dto.getDictValue())) {
            queryWrapper.lambda().like(CbvacDictEntity::getDictValue, dto.getDictValue());
        }
        IPage<CbvacDictEntity> page = super.page(dto.getPage(), queryWrapper);
        PageVo<CbvacDictPageVo> pageVo = PageUtil.pageToPageVo(page, CbvacDictPageVo.class);
        this.setStatusValue(pageVo);
        return new ResultVo(ResultEnum.SUCCESS, pageVo);
    }


    @Override
    public ResultVo findUnitTypePage(CbvacDictPageDto dto) {
        List<DictTypeEnum> dictTypeEnumList = DictTypeEnum.findByCode(DictTypeEnum.WEIGHT_UNIT.getCode());
        List<String> dictTypeValues = dictTypeEnumList.stream().map(DictTypeEnum::getType).collect(Collectors.toList());
        dto.setParentValues(dictTypeValues);
        IPage iPage = super.baseMapper.selectUnit(dto.getPage(), dto);
        PageVo<CbvacDictPageVo> pageVo = PageUtil.pageToPageVo(iPage, CbvacDictPageVo.class);
        this.setStatusValue(pageVo);
        return new ResultVo(ResultEnum.SUCCESS, pageVo);
    }

    @Override
    public CbvacDictPageVo findDictById(Long id) {
        CbvacDictEntity cbvacDictEntity = super.getById(id);
        CbvacDictPageVo vo = null;
        if (null != cbvacDictEntity) {
            vo = new CbvacDictPageVo();
            BeanUtils.copyProperties(cbvacDictEntity, vo);
        }
        return vo;
    }

    @Transactional
    @Override
    public ResultVo editDict(CbvacDictEditDto dto) {
        CbvacDictEntity cbvacDictEntity = getById(dto.getId());
        if (null == cbvacDictEntity) {
            return new ResultVo(ResultEnum.DICT_TYPE_NOT_FOUND);
        }
        cbvacDictEntity.setDictName(dto.getDictName());
        super.saveOrUpdate(cbvacDictEntity);
        return ResultVo.success();
    }

    @Transactional
    @Override
    public ResultVo editStatus(Long id) {
        CbvacDictEntity cbvacDictEntity = getById(id);
        if (null == cbvacDictEntity) {
            return new ResultVo(ResultEnum.DICT_TYPE_NOT_FOUND);
        }
        if (cbvacDictEntity.getStatus().equals(DictValueEnum.getCodeValue(DictValueEnum.ENABLE))) {
            cbvacDictEntity.setStatus(DictValueEnum.getCodeValue(DictValueEnum.DISABLE));
        } else {
            cbvacDictEntity.setStatus(DictValueEnum.getCodeValue(DictValueEnum.ENABLE));
        }
        super.saveOrUpdate(cbvacDictEntity);
        return ResultVo.success();
    }

    @Override
    public ResultVo findUnitGroup() {
        List<DictTypeEnum> dictTypeEnumList = DictTypeEnum.findByCode(DictTypeEnum.WEIGHT_UNIT.getCode());
        List<CbvacDictPageVo> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dictTypeEnumList)) {
            List<String> dictTypes = dictTypeEnumList.stream().map(DictTypeEnum::getType).collect(Collectors.toList());
            List<CbvacDictEntity> cbvacDictEntities = super.list(new QueryWrapper<CbvacDictEntity>().lambda().in(CbvacDictEntity::getDictValue, dictTypes));
            CbvacDictPageVo vo = null;
            for (CbvacDictEntity cbvacDictEntity : cbvacDictEntities) {
                vo = new CbvacDictPageVo();
                BeanUtils.copyProperties(cbvacDictEntity, vo);
                list.add(vo);
            }
        }
        return new ResultVo(ResultEnum.SUCCESS, list);
    }

    @Transactional
    @Override
    public ResultVo insertUnitType(UnitTypeDto dto) {
        CbvacDictEntity parentEntity = getById(dto.getParentId());
        if (null == parentEntity) {
            return new ResultVo(ResultEnum.DICT_TYPE_NOT_FOUND);
        }
        List<DictTypeEnum> dictTypeEnumList = DictTypeEnum.findByCode(DictTypeEnum.WEIGHT_UNIT.getCode());
        CbvacDictEntity cbvacDictEntity = null;
        if (!CollectionUtils.isEmpty(dictTypeEnumList)) {
            List<String> dictTypes = dictTypeEnumList.stream().map(DictTypeEnum::getType).collect(Collectors.toList());
            cbvacDictEntity = super.getOne(new QueryWrapper<CbvacDictEntity>().lambda().in(CbvacDictEntity::getParentValue, dictTypes).eq(CbvacDictEntity::getDictValue, dto.getDictValue()));
            if (null != cbvacDictEntity) {
                return new ResultVo(ResultEnum.DICT_VALUE_REPEAT);
            }

        }
        cbvacDictEntity = new CbvacDictEntity();
        BeanUtils.copyProperties(dto, cbvacDictEntity);
        cbvacDictEntity.setParentValue(parentEntity.getDictValue());
        super.save(cbvacDictEntity);
        return ResultVo.success();
    }

    @Override
    public ResultVo<List<CbvacDictPageVo>> findAllNoPage() {
        List<DictTypeEnum> dictTypeEnumList = DictTypeEnum.findByCode(DictTypeEnum.WEIGHT_UNIT.getCode());
        List<String> dictTypeValues = dictTypeEnumList.stream().map(DictTypeEnum::getType).collect(Collectors.toList());
        List<CbvacDictPageVo> vos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dictTypeValues)) {
            List<CbvacDictEntity> dictEntityList = super.baseMapper.selectByParentValueIn(dictTypeValues);
            CbvacDictPageVo vo = null;
            for (CbvacDictEntity cbvacDictEntity : dictEntityList) {
                vo = new CbvacDictPageVo();
                BeanUtils.copyProperties(cbvacDictEntity, vo);
                vos.add(vo);
            }
        }
        return new ResultVo<>(ResultEnum.SUCCESS, vos);
    }

    /**
     * 验证字典值是否重复
     *
     * @param dto
     * @return
     */
    private boolean vlidataDictValue(String dictValue, Long parentId) {
        QueryWrapper<CbvacDictEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CbvacDictEntity::getDictValue, dictValue);
        if (null == parentId) {
            queryWrapper.lambda().isNull(CbvacDictEntity::getParentId);
        } else {
            queryWrapper.lambda().eq(CbvacDictEntity::getParentId, parentId);
        }
        CbvacDictEntity sysDictEntity = this.getOne(queryWrapper);
        if (null != sysDictEntity) {
            return true;
        }
        return false;
    }

    /**
     * 设置状态展示值
     *
     * @param pageVo
     */
    private void setStatusValue(PageVo<CbvacDictPageVo> pageVo) {
        List<CbvacDictPageVo> records = pageVo.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            records.stream().forEach(record -> {
                DictValueEnum dictValueEnum = DictValueEnum.findByTypeAndCode(DictTypeEnum.STATUS_TYPE, record.getStatus());
                if (null != dictValueEnum) {
                    record.setStatusValue(dictValueEnum.getDescribe());
                    if (record.getStatus().equals(DictValueEnum.getCodeValue(DictValueEnum.DISABLE))) {
                        record.setStatusBtnValue(DictValueEnum.ENABLE.getDescribe());
                    } else {
                        record.setStatusBtnValue(DictValueEnum.DISABLE.getDescribe());
                    }
                }
            });
        }
    }
}
