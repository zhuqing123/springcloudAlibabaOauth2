package com.cbvac.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhuqing
 * @Date: 2020-04-20-17:56
 * @Description:
 */
public class EasyExcelListener<T> extends AnalysisEventListener<T> {

    public static final Logger LOGGER= LoggerFactory.getLogger(EasyExcelListener.class);

    private List<T> datas = new ArrayList<T>();

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        datas.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOGGER.info(JSON.toJSONString(datas));
    }

    public List<T> getDatas() {
        return datas;
    }
}
