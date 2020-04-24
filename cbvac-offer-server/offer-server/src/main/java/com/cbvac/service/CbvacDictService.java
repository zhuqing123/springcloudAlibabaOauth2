package com.cbvac.service;

import com.cbvac.dto.CbvacDictDto;
import com.cbvac.dto.CbvacDictEditDto;
import com.cbvac.dto.CbvacDictPageDto;
import com.cbvac.dto.UnitTypeDto;
import com.cbvac.entity.CbvacDictEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cbvac.vo.CbvacDictPageVo;
import com.cbvac.vo.ResultVo;

import java.util.List;


/**
 * <p>
 * 字典配置表 服务类
 * </p>
 *
 * @author zhuqing
 * @since 2020-04-17
 */
public interface CbvacDictService extends IService<CbvacDictEntity> {


    /**
     * 同步k3字典类型
     *
     * @return
     */
    Boolean synchronousK3DictType();

    /**
     * 同步k3字典
     *
     * @param
     * @return
     */
    Boolean synchronousK3Dict();

    /**
     * 通过字典名称查询字典
     *
     * @param dictName
     * @return
     */
    CbvacDictEntity findByDictName(String dictName);

    /**
     * 通过选择类型添加字典
     *
     * @param parentId
     * @param dto
     * @return
     */
    ResultVo insertSysDict(String parentValue, CbvacDictDto dto);

    /**
     * 添加字典类型
     *
     * @param dto
     * @return
     */
    ResultVo insertSysDictType(CbvacDictDto dto);

    /**
     * 通过批量字典名称查询字典
     *
     * @param dictUnitNames
     * @return
     */
    List<CbvacDictEntity> findByDictNameIn(List<String> dictUnitNames);

    /**
     * 通过字典类型id分页查询字典
     * @param dto
     * @return
     */
    ResultVo findDictByParentTypeAllPage(CbvacDictPageDto dto);

    /**
     * 查询计量单位
     * @param dto
     * @return
     */
    ResultVo findUnitTypePage(CbvacDictPageDto dto);

    /**
     * 根据id查询对应字典
     * @param id
     * @return
     */
    CbvacDictPageVo findDictById(Long id);

    /**
     * 编辑字典，只能修改名称
     * @param dto
     * @return
     */
    ResultVo editDict(CbvacDictEditDto dto);

    /**
     * 启用停用
     * @param id
     * @return
     */
    ResultVo editStatus(Long id);

    /**
     * 查询计量单位分组
     * @return
     */
    ResultVo findUnitGroup();

    /**
     * 新增计量单位
     * @param dto
     * @return
     */
    ResultVo insertUnitType(UnitTypeDto dto);

    /**
     * 查询所有无分页计量单位列表
     * @param dto
     * @return
     */
    ResultVo<List<CbvacDictPageVo>> findAllNoPage();
}
