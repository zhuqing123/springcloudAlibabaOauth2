package com.cbvac.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-14:36
 * @Description: 公共字段填充
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setIfAbsent("createTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setIfAbsent("modifyTime", new Date(), metaObject);
    }

    private void setIfAbsent(String fieldName, Object value, MetaObject metaObject) {
        if (getFieldValByName(fieldName, metaObject) != null) {
            return;
        }
        setFieldValByName(fieldName, value, metaObject);
    }
}
