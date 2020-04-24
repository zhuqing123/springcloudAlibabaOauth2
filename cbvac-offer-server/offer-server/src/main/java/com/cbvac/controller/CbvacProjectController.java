package com.cbvac.controller;


import com.cbvac.constant.UrlConstant;
import com.cbvac.dto.ProjectDto;
import com.cbvac.dto.ProjectPageDto;
import com.cbvac.enums.ResultEnum;
import com.cbvac.service.CbvacProjectService;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ProjectVo;
import com.cbvac.vo.ResultVo;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 项目表 前端控制器
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-24
 */
@RestController
@RequestMapping(value = UrlConstant.BACKGROUND_URL + "/cbvac")
public class CbvacProjectController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacProjectController.class);

    @Autowired
    private CbvacProjectService projectService;


    @ApiOperation(value = "项目分页列表")
    @GetMapping(value = "/project")
    public ResultVo<PageVo<ProjectVo>> findAllPage(@Validated ProjectPageDto dto) {
        return this.projectService.findAllPage(dto);
    }

    @ApiOperation(value = "新增项目")
    @PostMapping(value = "/project")
    public ResultVo insetProject(@Validated ProjectDto dto) {
        return this.projectService.insetProject(dto);
    }

    @ApiOperation(value = "编辑回显")
    @GetMapping(value = "/project/{id}")
    public ResultVo<ProjectVo> findProjectById(@PathVariable Long id) {
        ProjectVo vo = this.projectService.findProjectById(id);
        if (null != vo) {
            return new ResultVo<>(ResultEnum.SUCCESS, vo);
        }
        return new ResultVo<>(ResultEnum.PROJECT_NOT_FOUND);
    }

    @ApiOperation(value = "编辑项目")
    @PutMapping(value = "/project/{id}")
    public ResultVo editProject(@PathVariable Long id, @Validated ProjectDto dto) {
        return this.projectService.editProject(id, dto);
    }


}

