package com.cbvac.service;

import com.cbvac.dto.CbvacProcessTemplateDto;
import com.cbvac.dto.ProcessDto;
import com.cbvac.dto.ProcessPageDto;
import com.cbvac.entity.CbvacProcessEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cbvac.vo.PageVo;
import com.cbvac.vo.ProcessVo;
import com.cbvac.vo.ResultVo;

import java.util.List;

/**
 * <p>
 * 工序配置 服务类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-21
 */
public interface CbvacProcessService extends IService<CbvacProcessEntity> {

    /**
     * 工序模板导入
     *
     * @param dtos
     * @return
     */
    ResultVo uploadProcess(List<CbvacProcessTemplateDto> dtos);

    /**
     * 工序列表
     *
     * @param dto
     * @return
     */
    ResultVo<PageVo<ProcessVo>> findAllPage(ProcessPageDto dto);

    /**
     * 添加工序
     *
     * @param dto
     * @return
     */
    ResultVo insertProcess(ProcessDto dto);

    /**
     * 通过id查询工序
     *
     * @param id
     * @return
     */
    ProcessVo findProcessById(Long id);

    /**
     * 编辑工序
     * @param id
     * @param dto
     * @return
     */
    ResultVo editProcess(Long id, ProcessDto dto);


    /**
     * 启用停用
     * @param id
     * @return
     */
    ResultVo<ProcessVo> editProcessStatus(Long id);
}
