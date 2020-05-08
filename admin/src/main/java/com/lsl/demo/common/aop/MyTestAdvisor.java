package com.lsl.demo.common.aop;

import com.lsl.demo.common.annotation.aop.MyTest;
import com.lsl.demo.utils.global.BaseContextHandler;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * @author lisiliang
 * @since 2020/3/12
 */
@Slf4j
@Component
public class MyTestAdvisor implements Pointcut, PointcutAdvisor {

    @Override
    public Advice getAdvice() {
        return (MethodInterceptor)invocation -> {
            System.out.println("mytest.advisor");
            Object rs = invocation.proceed();
            Object[] param = invocation.getArguments();
            String url = BaseContextHandler.getRequest().getRequestURI();
            String method = invocation.getMethod().getName();
            log.info("url:" + url + ",method:" + method + ", param:" + Arrays.toString(param) + ", rs:" + rs);
            return rs;
        };
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> true;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new StaticMethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> aClass) {
                return method.isAnnotationPresent(MyTest.class);
            }
        };
    }

    @Override
    public Pointcut getPointcut() {
        return this;
    }

}
