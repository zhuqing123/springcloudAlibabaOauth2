package com.cbvac.service;

import com.cbvac.dto.ProjectDto;
import com.cbvac.dto.ProjectPageDto;
import com.cbvac.entity.CbvacProjectEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ProjectVo;
import com.cbvac.vo.ResultVo;

/**
 * <p>
 * 项目表 服务类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-24
 */
public interface CbvacProjectService extends IService<CbvacProjectEntity> {

    /**
     * 分页查询项目表
     * @param dto
     * @return
     */
    ResultVo<PageVo<ProjectVo>> findAllPage(ProjectPageDto dto);

    /**
     * 新增项目
     * @param dto
     * @return
     */
    ResultVo insetProject(ProjectDto dto);

    /**
     * 通过id查询项目
     * @param id
     * @return
     */
    ProjectVo findProjectById(Long id);

    /**
     * 编辑项目
     * @param id
     * @param dto
     * @return
     */
    ResultVo editProject(Long id, ProjectDto dto);
}
