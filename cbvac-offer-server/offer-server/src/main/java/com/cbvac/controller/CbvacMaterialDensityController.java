package com.cbvac.controller;


import com.alibaba.excel.EasyExcel;
import com.cbvac.constant.UrlConstant;
import com.cbvac.dto.CbvacMaterialDensityTemplateDto;
import com.cbvac.dto.MaterialDensityDto;
import com.cbvac.dto.MaterialDensityPageDto;
import com.cbvac.enums.ResultEnum;
import com.cbvac.listener.EasyExcelListener;
import com.cbvac.service.CbvacMaterialDensityService;
import com.cbvac.vo.MaterialDensityPageVo;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 材质密度表 前端控制器
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-20
 */
@Api(tags = {"材质密度"})
@RestController
@RequestMapping(UrlConstant.BACKGROUND_URL + "/cbvac")
public class CbvacMaterialDensityController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacMaterialDensityController.class);

    @Autowired
    private CbvacMaterialDensityService materialDensityService;


    @ApiIgnore
    @ApiOperation("导入密度模板下载")
    @GetMapping("/material/density/template")
    public void downloadTemplate(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("材质密度模板", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), CbvacMaterialDensityTemplateDto.class)
                .autoCloseStream(Boolean.FALSE).sheet("材质密度")
                .doWrite(null);
    }

    @ApiIgnore
    @ApiOperation(value = "材质密度excel上传")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(value = "文件", required = true, dataTypeClass = MultipartFile.class)
    })
    @PostMapping(value = "/material/density/template")
    public ResultVo uploadMaterialDensity(@RequestParam MultipartFile file) throws IOException {
        List<CbvacMaterialDensityTemplateDto> dtos = EasyExcel.read(file.getInputStream(), CbvacMaterialDensityTemplateDto.class, new EasyExcelListener<CbvacMaterialDensityTemplateDto>()).sheet(0).doReadSync();
        return this.materialDensityService.uploadMaterialDensity(dtos);
    }

    @ApiOperation(value = "材质密度列表")
    @GetMapping("/material/density")
    public ResultVo findAllPage(@Validated MaterialDensityPageDto dto) {
        return this.materialDensityService.findAllPage(dto);
    }

    @ApiOperation(value = "新增材质密度")
    @PostMapping("/material/density")
    public ResultVo insertMaterialDensity(@Validated MaterialDensityDto dto) {
        return this.materialDensityService.insertMaterialDensity(dto);
    }

    @ApiOperation(value = "编辑回显")
    @GetMapping("/material/density/{id}")
    public ResultVo findById(@PathVariable Long id) {
        MaterialDensityPageVo vo = this.materialDensityService.findById(id);
        if (null != vo) {
            return new ResultVo(ResultEnum.SUCCESS, vo);
        }
        return new ResultVo(ResultEnum.MATERIAL_DENSITY_NOT_FOUND);
    }

    @ApiOperation(value = "编辑")
    @PutMapping("/material/density/{id}")
    public ResultVo editMaterialDensity(@PathVariable Long id, @Validated MaterialDensityDto dto) {
        return this.materialDensityService.editMaterialDensity(id, dto);
    }

    @ApiOperation(value = "材质密度下拉框")
    @GetMapping("/material/density/list")
    public ResultVo findAllNoPage() {
        return this.materialDensityService.findAllNoPage();
    }
}

