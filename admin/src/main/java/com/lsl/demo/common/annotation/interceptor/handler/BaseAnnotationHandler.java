package com.lsl.demo.common.annotation.interceptor.handler;

import com.lsl.demo.common.exceptions.BaseException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 注解功能实现类的基类，当需要增加新的注解时，创建方法继承该类即可
 * @author lisiliang
 * @since 2020/3/9
 */
public abstract class BaseAnnotationHandler {

    /**
     * 实现注解的功能
     * @param method
     * @param request
     * @throws BaseException
     */
    public abstract void headle(Method method, HttpServletRequest request) throws BaseException;



}
