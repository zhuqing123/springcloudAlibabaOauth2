package com.cbvac.controller;


import com.cbvac.constant.UrlConstant;
import com.cbvac.dto.CbvacDictDto;
import com.cbvac.dto.CbvacDictEditDto;
import com.cbvac.dto.CbvacDictPageDto;
import com.cbvac.dto.UnitTypeDto;
import com.cbvac.enums.DictTypeEnum;
import com.cbvac.enums.ResultEnum;
import com.cbvac.service.CbvacDictService;
import com.cbvac.vo.CbvacDictPageVo;
import com.cbvac.vo.ResultVo;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典配置表 前端控制器
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-17
 */
@Api(tags = {"字典配置"})
@RestController
@RequestMapping(value = UrlConstant.BACKGROUND_URL + "/cbvac")
public class CbvacDictController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacDictController.class);

    @Autowired
    private CbvacDictService sysDictService;


    @ApiOperation(value = "生产类型列表")
    @GetMapping(value = "/dict/production/type")
    public ResultVo findProductionTypePage(@Validated CbvacDictPageDto dto) {
        dto.setParentValue(DictTypeEnum.PRODUCTION_TYPE.getType());
        return this.sysDictService.findDictByParentTypeAllPage(dto);
    }


    @ApiModelProperty(value = "新增生产类型")
    @PostMapping(value = "/dict/production/type")
    public ResultVo insertProductionType(@Validated CbvacDictDto dto) {
        return this.sysDictService.insertSysDict(DictTypeEnum.PRODUCTION_TYPE.getType(), dto);
    }

    @ApiModelProperty(value = "编辑生产类型")
    @PutMapping(value = "/dict")
    public ResultVo editDict(CbvacDictEditDto dto) {
        return this.sysDictService.editDict(dto);
    }

    @ApiOperation(value = "原材料类型列表")
    @GetMapping(value = "/dict/material/type")
    public ResultVo findMaterialTypePage(@Validated CbvacDictPageDto dto) {
        dto.setParentValue(DictTypeEnum.RAW_MATERIAL_TYPE.getType());
        return this.sysDictService.findDictByParentTypeAllPage(dto);
    }

    @ApiModelProperty(value = "新增原材料类型")
    @PostMapping(value = "/dict/material/type")
    public ResultVo insertMaterialType(@Validated CbvacDictDto dto) {
        return this.sysDictService.insertSysDict(DictTypeEnum.RAW_MATERIAL_TYPE.getType(), dto);
    }

    @ApiOperation(value = "计量单位列表")
    @GetMapping(value = "/dict/unit/type")
    public ResultVo findUnitTypePage(@Validated CbvacDictPageDto dto) {
        return this.sysDictService.findUnitTypePage(dto);
    }

    @ApiOperation(value = "编辑回显")
    @ApiImplicitParam(name = "id",value = "主键",paramType = "path",required = true)
    @GetMapping(value = "/dict/{id}")
    public ResultVo findDictById(@PathVariable Long id){
        CbvacDictPageVo vo = this.sysDictService.findDictById(id);
        if (null!=vo){
            return new ResultVo(ResultEnum.SUCCESS,vo);
        }
        return new ResultVo(ResultEnum.DICT_NOT_FOUND);
    }

    @ApiOperation(value = "启用停用")
    @ApiImplicitParam(name = "id",value = "主键",paramType = "path",required = true)
    @PutMapping(value = "/dict/{id}")
    public ResultVo editStatus(@PathVariable Long id){
        return this.sysDictService.editStatus(id);
    }

    @ApiOperation(value = "查询计量单位分组")
    @GetMapping(value = "/dict/unit/group")
    public ResultVo findUnitGroup(){
        return this.sysDictService.findUnitGroup();
    }

    @ApiOperation(value = "新增计量单位列表")
    @PostMapping(value = "/dict/unit/type")
    public ResultVo insertUnitType(@Validated UnitTypeDto dto) {
        return this.sysDictService.insertUnitType(dto);
    }

    @ApiOperation(value = "查询所有无分页计量单位列表")
    @GetMapping(value = "/dict/unit/type/list")
    public ResultVo<List<CbvacDictPageVo>> findAllNoPage() {
        return this.sysDictService.findAllNoPage();
    }


}

