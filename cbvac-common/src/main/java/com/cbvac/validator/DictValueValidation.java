package com.cbvac.validator;

import com.cbvac.annotation.DictValue;
import com.cbvac.enums.DictTypeEnum;
import com.cbvac.enums.DictValueEnum;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhuqing
 * @Date: 2020-04-24-13:53
 * @Description:
 */
public class DictValueValidation implements ConstraintValidator<DictValue, Object> {

    private static List<String> list = new ArrayList<String>();

    @Override
    public void initialize(DictValue constraintAnnotation) {
        DictTypeEnum dictTypeEnum = constraintAnnotation.dictType();
        List<DictValueEnum> dictValueEnumList = DictValueEnum.findByDictType(dictTypeEnum);
        list = dictValueEnumList.stream().map(DictValueEnum::getCode).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String s = o.toString();
        if (StringUtils.isBlank(s)) {
            return true;
        }
        return list.contains(s);
    }
}
