package com.cbvac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cbvac.dto.ProjectDto;
import com.cbvac.dto.ProjectPageDto;
import com.cbvac.entity.CbvacProjectEntity;
import com.cbvac.enums.DictValueEnum;
import com.cbvac.enums.ResultEnum;
import com.cbvac.mapper.CbvacProjectMapper;
import com.cbvac.service.CbvacProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cbvac.utils.PageUtil;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ProjectVo;
import com.cbvac.vo.ResultVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 项目表 服务实现类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-24
 */
@Service
public class CbvacProjectServiceImpl extends ServiceImpl<CbvacProjectMapper, CbvacProjectEntity> implements CbvacProjectService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacProjectServiceImpl.class);

    @Override
    public ResultVo<PageVo<ProjectVo>> findAllPage(ProjectPageDto dto) {
        QueryWrapper<CbvacProjectEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(dto.getProjectName())) {
            queryWrapper.lambda().like(CbvacProjectEntity::getProjectName, dto.getProjectName());
        }
        if (null != dto.getProjectType()) {
            queryWrapper.lambda().like(CbvacProjectEntity::getProjectType, dto.getProjectType());
        }
        Page page = super.page(dto.getPage(), queryWrapper);
        PageVo pageVo = PageUtil.pageToPageVo(page, ProjectVo.class);
        List<ProjectVo> records = pageVo.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            for (ProjectVo record : records) {
                if (record.getProjectType().equals(DictValueEnum.SYSTEM_INTEGRATION.getCodeToInt())) {
                    record.setProjectTypeName(DictValueEnum.SYSTEM_INTEGRATION.getDescribe());
                } else {
                    record.setProjectTypeName(DictValueEnum.CUSTOM_MADE.getDescribe());
                }
            }
        }
        return new ResultVo<>(ResultEnum.SUCCESS, pageVo);
    }

    @Transactional
    @Override
    public ResultVo insetProject(ProjectDto dto) {
        CbvacProjectEntity projectEntity = new CbvacProjectEntity();
        BeanUtils.copyProperties(dto, projectEntity);
        super.save(projectEntity);
        return ResultVo.success();
    }

    @Override
    public ProjectVo findProjectById(Long id) {
        CbvacProjectEntity projectEntity = super.getById(id);
        ProjectVo vo = null;
        if (null != projectEntity) {
            vo = new ProjectVo();
            BeanUtils.copyProperties(projectEntity, vo);
            if (vo.getProjectType().equals(DictValueEnum.SYSTEM_INTEGRATION.getCodeToInt())) {
                vo.setProjectTypeName(DictValueEnum.SYSTEM_INTEGRATION.getDescribe());
            } else {
                vo.setProjectTypeName(DictValueEnum.CUSTOM_MADE.getDescribe());
            }
        }
        return vo;
    }

    @Transactional
    @Override
    public ResultVo editProject(Long id, ProjectDto dto) {
        CbvacProjectEntity projectEntity = super.getById(id);
        if (null == projectEntity) {
            return new ResultVo<>(ResultEnum.PROJECT_NOT_FOUND);
        }
        BeanUtils.copyProperties(dto, projectEntity);
        super.updateById(projectEntity);
        return ResultVo.success();
    }
}
