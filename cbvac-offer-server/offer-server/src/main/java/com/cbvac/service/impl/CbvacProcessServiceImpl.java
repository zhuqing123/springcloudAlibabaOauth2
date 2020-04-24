package com.cbvac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cbvac.dto.CbvacProcessTemplateDto;
import com.cbvac.dto.ProcessDto;
import com.cbvac.dto.ProcessPageDto;
import com.cbvac.entity.CbvacDictEntity;
import com.cbvac.entity.CbvacProcessEntity;
import com.cbvac.enums.DictValueEnum;
import com.cbvac.enums.ResultEnum;
import com.cbvac.mapper.CbvacProcessMapper;
import com.cbvac.service.CbvacDictService;
import com.cbvac.service.CbvacProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbvac.utils.PageUtil;
import com.cbvac.vo.CbvacDictPageVo;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ProcessVo;
import com.cbvac.vo.ResultVo;
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
 * 工序配置 服务实现类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
@Service
public class CbvacProcessServiceImpl extends ServiceImpl<CbvacProcessMapper, CbvacProcessEntity> implements CbvacProcessService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacProcessServiceImpl.class);

    @Autowired
    private CbvacDictService cbvacDictService;

    @Transactional
    @Override
    public ResultVo uploadProcess(List<CbvacProcessTemplateDto> dtos) {
        if (!CollectionUtils.isEmpty(dtos)) {
            List<String> dictUnitNames = dtos.stream().filter(dto -> StringUtils.isNoneBlank(dto.getDictUnitName())).map(CbvacProcessTemplateDto::getDictUnitName).collect(Collectors.toList());
            List<CbvacDictEntity> dictEntityList = cbvacDictService.findByDictNameIn(dictUnitNames);
            CbvacProcessEntity processEntity = null;
            Optional<CbvacDictEntity> optional = null;
            List<CbvacProcessEntity> list = new ArrayList<>();
            for (CbvacProcessTemplateDto dto : dtos) {
                processEntity = new CbvacProcessEntity();
                BeanUtils.copyProperties(dto, processEntity);
                if (!CollectionUtils.isEmpty(dictEntityList)) {
                    optional = dictEntityList.stream().filter(dictEntity -> StringUtils.equals(dto.getDictUnitName(), dictEntity.getDictName())).findFirst();
                    if (optional.isPresent()) {
                        processEntity.setDictUnitId(optional.get().getId());
                    }
                }
                list.add(processEntity);
            }
            super.saveBatch(list);
        }
        return ResultVo.success();
    }

    @Override
    public ResultVo<PageVo<ProcessVo>> findAllPage(ProcessPageDto dto) {
        IPage<CbvacProcessEntity> page = super.baseMapper.selfSelectPageList(dto.getPage(), dto);
        PageVo<ProcessVo> pageVo = PageUtil.pageToPageVo(page, ProcessVo.class);
        List<ProcessVo> vos = pageVo.getRecords();
        if (!CollectionUtils.isEmpty(vos)) {
            vos.stream()
                    .forEach(vo -> {
                        if (vo.getStatus().equals(DictValueEnum.ENABLE.getCodeToInt())) {
                            vo.setStatusValue(DictValueEnum.ENABLE.getDescribe());
                            vo.setStatusBtnValue(DictValueEnum.DISABLE.getDescribe());
                        } else {
                            vo.setStatusValue(DictValueEnum.DISABLE.getDescribe());
                            vo.setStatusBtnValue(DictValueEnum.ENABLE.getDescribe());
                        }
                    });
        }
        return new ResultVo<>(ResultEnum.SUCCESS, pageVo);
    }

    @Transactional
    @Override
    public ResultVo insertProcess(ProcessDto dto) {
        CbvacDictPageVo dictPageVo = this.cbvacDictService.findDictById(dto.getDictUnitId());
        if (null == dictPageVo) {
            return new ResultVo(ResultEnum.DICT_NOT_FOUND);
        }
        CbvacProcessEntity processEntity = new CbvacProcessEntity();
        BeanUtils.copyProperties(dto, processEntity);
        super.save(processEntity);
        return new ResultVo(ResultEnum.SUCCESS);
    }

    @Override
    public ProcessVo findProcessById(Long id) {
        CbvacProcessEntity processEntity = super.baseMapper.selfSelectById(id);
        ProcessVo vo = null;
        if (null != processEntity) {
            vo = new ProcessVo();
            BeanUtils.copyProperties(processEntity, vo);
        }
        return vo;
    }

    @Transactional
    @Override
    public ResultVo editProcess(Long id, ProcessDto dto) {
        CbvacDictPageVo dictPageVo = this.cbvacDictService.findDictById(dto.getDictUnitId());
        if (null == dictPageVo) {
            return new ResultVo(ResultEnum.DICT_NOT_FOUND);
        }
        CbvacProcessEntity processEntity = super.getById(id);
        if (null == processEntity) {
            new ResultVo<>(ResultEnum.PROCESS_NOT_FOUND);
        }
        BeanUtils.copyProperties(dto, processEntity);
        super.updateById(processEntity);
        return ResultVo.success();
    }

    @Transactional
    @Override
    public ResultVo<ProcessVo> editProcessStatus(Long id) {
        CbvacProcessEntity processEntity = super.getById(id);
        if (null == processEntity) {
            return new ResultVo<>(ResultEnum.PROCESS_NOT_FOUND);
        }
        if (processEntity.getStatus().equals(DictValueEnum.ENABLE.getCodeToInt())) {
            processEntity.setStatus(DictValueEnum.DISABLE.getCodeToInt());
        } else {
            processEntity.setStatus(DictValueEnum.ENABLE.getCodeToInt());
        }
        super.updateById(processEntity);
        return ResultVo.success();
    }
}
