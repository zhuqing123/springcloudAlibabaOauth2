package com.cbvac.controller;


import com.alibaba.excel.EasyExcel;
import com.cbvac.constant.UrlConstant;
import com.cbvac.dto.CbvacMaterialTypeTemplateDto;
import com.cbvac.dto.CbvacProcessTemplateDto;
import com.cbvac.dto.ProcessDto;
import com.cbvac.dto.ProcessPageDto;
import com.cbvac.enums.ResultEnum;
import com.cbvac.listener.EasyExcelListener;
import com.cbvac.service.CbvacProcessService;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ProcessVo;
import com.cbvac.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 工序配置 前端控制器
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
@Api(tags = {"工序配置"})
@RestController
@RequestMapping(UrlConstant.BACKGROUND_URL + "/cbvac")
public class CbvacProcessController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacProcessController.class);

    @Autowired
    private CbvacProcessService cbvacProcessService;

    @ApiIgnore
    @ApiOperation(value = "工序模板导入")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "文件", required = true, dataTypeClass = MultipartFile.class)
    })
    @PostMapping(value = "/process/template")
    public ResultVo uploadProcess(@RequestParam MultipartFile file) throws IOException {
        List<CbvacProcessTemplateDto> dtos = EasyExcel.read(file.getInputStream(), CbvacProcessTemplateDto.class, new EasyExcelListener<CbvacProcessTemplateDto>()).sheet(2).doReadSync();
        return this.cbvacProcessService.uploadProcess(dtos);
    }

    @ApiOperation(value = "工序列表")
    @GetMapping("/process")
    public ResultVo<PageVo<ProcessVo>> findAllPage(@Validated ProcessPageDto dto) {
        return this.cbvacProcessService.findAllPage(dto);
    }

    @ApiOperation(value = "添加工序")
    @PostMapping("/process")
    public ResultVo insertProcess(@Validated ProcessDto dto) {
        return this.cbvacProcessService.insertProcess(dto);
    }

    @ApiOperation(value = "编辑回显")
    @GetMapping("/process/{id}")
    public ResultVo<ProcessVo> findProcessById(@PathVariable Long id) {
        ProcessVo vo = this.cbvacProcessService.findProcessById(id);
        if (null != vo) {
            return new ResultVo<>(ResultEnum.SUCCESS, vo);
        }
        return new ResultVo<>(ResultEnum.PROCESS_NOT_FOUND);
    }

    @ApiOperation(value = "编辑工序")
    @PostMapping("/process/{id}")
    public ResultVo editProcess(@PathVariable Long id, @Validated ProcessDto dto) {
        return this.cbvacProcessService.editProcess(id, dto);
    }

    @ApiOperation(value = "启用停用")
    @PatchMapping("/process/{id}")
    public ResultVo<ProcessVo> editProcessStatus(@PathVariable Long id) {
        return this.cbvacProcessService.editProcessStatus(id);
    }


}

