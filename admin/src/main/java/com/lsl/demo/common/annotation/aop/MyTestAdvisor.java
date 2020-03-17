package com.lsl.demo.common.annotation.aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author lisiliang
 * @since 2020/3/12
 */

@Deprecated
@Aspect
public class MyTestAdvisor implements Pointcut, PointcutAdvisor {

    @Override
    public Advice getAdvice() {
        return (MethodInterceptor)invocation -> {
            System.out.println("mytest.advisor");
            Object rs = invocation.proceed();
            String className = invocation.getThis().getClass().getName();
            String methodName = invocation.getMethod().getName();
            String methods = className + "." + methodName + "()";
            Object[] args = invocation.getArguments();
            String param = Arrays.toString(args);
            HttpServletRequest request =
                    ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String contentType = request.getContentType();
            System.out.println(request);
            return rs;
        };
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> false;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new StaticMethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> aClass) {
                if (method.isAnnotationPresent(MyTest.class)) {
                    System.out.println(method.getDeclaringClass().getName());
                    System.out.println(method.getAnnotation(MyTest.class).value());
                }
                if (Objects.nonNull(method.getAnnotation(MyTest.class))) {
                    System.out.println(method.getDeclaringClass().getName());
                }
                return Objects.nonNull(method.getAnnotation(MyTest.class));
            }
        };
    }

    @Override
    public Pointcut getPointcut() {
        return this;
    }

}
