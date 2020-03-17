package com.lsl.demo.common.annotation.interceptor.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 用于处理权限拦截器的中注解
 * @author lisiliang
 * @since 2020/3/9
 */
@Configuration
public class Handler {

    private ApplicationContext applicationContext;

    public Handler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void handle(Method method, HttpServletRequest request) {
        Map<String, BaseAnnotationHandler> baseAnnotationHandlerMap =
                applicationContext.getBeansOfType(BaseAnnotationHandler.class);
        for (BaseAnnotationHandler object : baseAnnotationHandlerMap.values()) {
            object.headle(method, request);
        }
    }

}
