package com.cbvac.controller;


import com.alibaba.excel.EasyExcel;
import com.cbvac.constant.UrlConstant;
import com.cbvac.dto.CbvacStandardPartsTemplateDto;
import com.cbvac.dto.StandardPartsDto;
import com.cbvac.dto.StandardPartsOutDto;
import com.cbvac.dto.StandardPartsPageDto;
import com.cbvac.enums.DictValueEnum;
import com.cbvac.enums.ResultEnum;
import com.cbvac.listener.EasyExcelListener;
import com.cbvac.service.CbvacStandardPartsService;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ResultVo;
import com.cbvac.vo.StandardPartsVo;
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

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 自制标准件 前端控制器
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
@Api(tags = {"自制标准件"})
@Validated
@RestController
@RequestMapping(UrlConstant.BACKGROUND_URL + "/cbvac")
public class CbvacStandardPartsController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacStandardPartsController.class);

    @Autowired
    private CbvacStandardPartsService cbvacHomemadeStandardService;

    @ApiOperation(value = "自制标准件模板导入")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "文件", required = true, dataTypeClass = MultipartFile.class)
    })
    @PostMapping(value = "/standard/parts/template")
    public ResultVo uploadHomemadeStandard(@RequestParam MultipartFile file) throws IOException {
        List<CbvacStandardPartsTemplateDto> dtos = EasyExcel.read(file.getInputStream(), CbvacStandardPartsTemplateDto.class, new EasyExcelListener<CbvacStandardPartsTemplateDto>()).sheet(3).doReadSync();
        return this.cbvacHomemadeStandardService.uploadHomemadeStandard(dtos);
    }

    @ApiOperation(value = "自制标准品列表")
    @GetMapping(value = "/standard/parts")
    public ResultVo<PageVo<StandardPartsVo>> findAllSelfPage(@Validated StandardPartsPageDto dto) {
        dto.setType(DictValueEnum.HOMEMADE_STANDARD.getCodeToInt());
        dto.setBrand(null);
        return this.cbvacHomemadeStandardService.findAllPage(dto);
    }

    @ApiOperation(value = "外购标准品列表")
    @GetMapping(value = "/standard/parts/out")
    public ResultVo<PageVo<StandardPartsVo>> findAllOutPage(@Validated StandardPartsPageDto dto) {
        dto.setType(DictValueEnum.OUTSOURCE_STANDARD_PARTS.getCodeToInt());
        return this.cbvacHomemadeStandardService.findAllPage(dto);
    }

    @ApiOperation(value = "查看外购标准件与自制标准件")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path")
    })
    @GetMapping(value = "/standard/parts/{id}")
    public ResultVo<StandardPartsVo> findStandardPartsById(@PathVariable Long id) {
        StandardPartsVo vo = this.cbvacHomemadeStandardService.findStandardPartsById(id);
        if (null != vo) {
            return new ResultVo<>(ResultEnum.SUCCESS, vo);
        }
        return new ResultVo<>(ResultEnum.DATA_NOT_FOUND);
    }

    @ApiOperation(value = "新增自制标准品")
    @PostMapping("/standard/parts")
    public ResultVo insetStandardParts(@Validated StandardPartsDto dto) {
        dto.setType(DictValueEnum.HOMEMADE_STANDARD.getCodeToInt());
        return this.cbvacHomemadeStandardService.insetStandardParts(dto);
    }

    @ApiOperation(value = "新增外购标准品")
    @PostMapping("/standard/parts/out")
    public ResultVo insetStandardPartsOut(@Validated StandardPartsOutDto dto) {
        dto.setType(DictValueEnum.OUTSOURCE_STANDARD_PARTS.getCodeToInt());
        return this.cbvacHomemadeStandardService.insetStandardParts(dto);
    }

    @ApiOperation(value = "编辑自制标准品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path")
    })
    @PutMapping("/standard/parts/{id}")
    public ResultVo editStandardParts(@PathVariable Long id, @Validated StandardPartsDto dto) {
        dto.setType(DictValueEnum.HOMEMADE_STANDARD.getCodeToInt());
        return this.cbvacHomemadeStandardService.editStandardParts(id, dto);
    }

    @ApiOperation(value = "编辑自外购标准品")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "path")
    })
    @PutMapping("/standard/parts/out/{id}")
    public ResultVo editStandardPartsOut(@PathVariable Long id, @Validated StandardPartsOutDto dto) {
        dto.setType(DictValueEnum.OUTSOURCE_STANDARD_PARTS.getCodeToInt());
        return this.cbvacHomemadeStandardService.editStandardParts(id, dto);
    }

}

