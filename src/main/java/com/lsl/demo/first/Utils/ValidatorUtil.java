package com.lsl.demo.first.Utils;

import cn.hutool.core.collection.CollectionUtil;
import com.lsl.demo.first.Utils.exceptions.ValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 才发现，是使用的java自带的校验工具啊
 * <p>
 *     校验工具类
 * </p>
 * @author lisiliang
 * @since 2020/1/11
 */
public class ValidatorUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验用
     * @param obj           待校验的对象
     * @param groups        待校验的组
     * @throws ValidationException
     */
    public static void validateEntity(Object obj, Class<?>... groups) throws ValidationException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, groups);
        if (!CollectionUtil.isNotEmpty(constraintViolations)) {
            String msg = constraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));
            throw new ValidationException(msg);
        }
    }

}
