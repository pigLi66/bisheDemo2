package com.lsl.demo.common.aop;

import com.lsl.demo.common.base.controller.BaseController;
import com.lsl.demo.utils.validate.*;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Validation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author lisiliang
 * @since 2020/4/1
 */

@Component
public class ValidatorAdvisor implements Pointcut, PointcutAdvisor {
    @Override
    public Advice getAdvice() {
        return (MethodInterceptor) invocation -> {
            Method method = invocation.getMethod();
            Class<?> group = null;
            if (method.isAnnotationPresent(GetMapping.class)) {
                group = SelectGroup.class;
            } else if (method.isAnnotationPresent(PutMapping.class)) {
                group = UpdateGroup.class;
            } else if (method.isAnnotationPresent(PostMapping.class)) {
                group = AddGroup.class;
            } else if (method.isAnnotationPresent(DeleteMapping.class)) {
                group = DeleteGroup.class;
            }
            Object[] params = invocation.getArguments();
            if (Objects.isNull(group)) {
                Arrays.stream(params).forEach(ValidatorUtil::validateEntity);
            } else {
                Class<?> finalGroup = group;
                Arrays.stream(params).forEach(item->ValidatorUtil.validateEntity(item, finalGroup));
            }
            return invocation.proceed();
        };
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> {
            while (clazz != Object.class) {
                if (clazz == BaseController.class) {
                    return true;
                }
                clazz = clazz.getSuperclass();
            }
            return false;
        };
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new StaticMethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return true;
            }
        };
    }

    @Override
    public Pointcut getPointcut() {
        return this;
    }
}
