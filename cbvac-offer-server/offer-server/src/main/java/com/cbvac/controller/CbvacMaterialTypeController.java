package com.cbvac.controller;


import com.alibaba.excel.EasyExcel;
import com.cbvac.constant.UrlConstant;
import com.cbvac.dto.CbvacMaterialTypeTemplateDto;
import com.cbvac.dto.MaterialTypeDto;
import com.cbvac.dto.MaterialTypePageDto;
import com.cbvac.enums.ResultEnum;
import com.cbvac.listener.EasyExcelListener;
import com.cbvac.service.CbvacMaterialTypeService;
import com.cbvac.vo.MaterialTypePageVo;
import com.cbvac.vo.PageVo;
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
 * 材质类型表 前端控制器
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-20
 */
@Api(tags = {"材质类型"})
@RestController
@RequestMapping(UrlConstant.BACKGROUND_URL + "/cbvac")
public class CbvacMaterialTypeController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacMaterialTypeController.class);

    @Autowired
    private CbvacMaterialTypeService materialTypeService;

    @ApiIgnore
    @ApiOperation(value = "材质类型管理excel上传")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "文件", required = true, dataTypeClass = MultipartFile.class)
    })
    @PostMapping(value = "/material/type/template")
    public ResultVo uploadMaterialType(@RequestParam MultipartFile file) throws IOException {
        List<CbvacMaterialTypeTemplateDto> dtos = EasyExcel.read(file.getInputStream(), CbvacMaterialTypeTemplateDto.class, new EasyExcelListener<CbvacMaterialTypeTemplateDto>()).sheet(1).doReadSync();
        return this.materialTypeService.uploadMaterialType(dtos);
    }

    @ApiOperation(value = "材质类型分页列表")
    @GetMapping(value = "/material/type")
    public ResultVo<PageVo<MaterialTypePageVo>> findAllPage(@Validated MaterialTypePageDto dto) {
        return this.materialTypeService.findAllPage(dto);
    }

    @ApiOperation(value = "添加材质类型")
    @PostMapping(value = "/material/type")
    public ResultVo insertMaterialType(@Validated MaterialTypeDto dto) {
        return this.materialTypeService.insertMaterialType(dto);
    }

    @ApiOperation(value = "编辑回显")
    @ApiImplicitParam(value = "主键", name = "id", paramType = "path", required = true)
    @GetMapping(value = "/material/type/{id}")
    public ResultVo<MaterialTypePageVo> findById(@PathVariable Long id) {
        MaterialTypePageVo vo = this.materialTypeService.findById(id);
        if (null != vo) {
            return new ResultVo<>(ResultEnum.SUCCESS, vo);
        }
        return new ResultVo<>(ResultEnum.MATERIAL_TYPE_NOT_FOUND);
    }

    @ApiOperation(value = "编辑")
    @ApiImplicitParam(value = "主键", name = "id", paramType = "path", required = true)
    @PutMapping(value = "/material/type/{id}")
    public ResultVo editMaterialType(@PathVariable Long id, @Validated MaterialTypeDto dto) {
        return this.materialTypeService.editMaterialType(id, dto);
    }

    @ApiOperation(value = "启用停用")
    @ApiImplicitParam(value = "主键", name = "id", paramType = "path", required = true)
    @PatchMapping(value = "/material/type/{id}")
    public ResultVo editMaterialTypeStatus(@PathVariable Long id) {
        return this.materialTypeService.editMaterialTypeStatus(id);
    }
}

