package com.cbvac.controller;


import com.cbvac.constant.UrlConstant;
import com.cbvac.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 子系统 前端控制器
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-24
 */
@Api(tags = {"子系统"})
@RestController
@RequestMapping(UrlConstant.BACKGROUND_URL + "/cbvac")
public class CbvacSystemController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CbvacSystemController.class);

    @ApiOperation(value = "通过项目id查询子系统")
    @GetMapping(value = "/system/projectId/{projectId}")
    public ResultVo findSystemByProjectId(@PathVariable Long projectId){
        return null;
    }

}

