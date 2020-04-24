package com.cbvac.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cbvac.vo.PageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuqing
 * @Date: 2020-04-22-10:10
 * @Description: mybatisPlus分页实例转换为自己的pageVo
 */
public class PageUtil implements Serializable {
    public static final Logger LOGGER= LoggerFactory.getLogger(PageUtil.class);

    public static PageVo pageToPageVo(IPage iPage, Class clazz) {
        PageVo pageVo = new PageVo();
        pageVo.setCurrentPage((int) iPage.getCurrent());
        pageVo.setTotal((int) iPage.getTotal());
        pageVo.setPageSize((int) iPage.getSize());
        pageVo.setPages((int) iPage.getPages());
        pageVo.setHitCount(iPage.isHitCount());
        pageVo.setSearchCount(iPage.isSearchCount());
        List sourceRecords = iPage.getRecords();
        List targetRecords = new ArrayList<>();
        try {
            if (!CollectionUtils.isEmpty(sourceRecords)) {
                Object targetRecord = null;
                for (Object sourceRecord : sourceRecords) {
                    targetRecord = clazz.newInstance();
                    BeanUtils.copyProperties(sourceRecord, targetRecord);
                    targetRecords.add(targetRecord);
                }
            }
            pageVo.setRecords(targetRecords);
        } catch (Exception e) {
            LOGGER.error("分页copy错误",e);
        }

        return pageVo;
    }
}
