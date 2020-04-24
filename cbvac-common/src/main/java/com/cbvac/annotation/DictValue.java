package com.cbvac.annotation;

import com.cbvac.enums.DictTypeEnum;
import com.cbvac.validator.DictValueValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author: zhuqing
 * @Date: 2020-04-24-14:12
 * @Description:
 */
@Constraint(validatedBy = DictValueValidation.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DictValue {

    String message() default "请重新选择字典数据";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    DictTypeEnum dictType();
}

