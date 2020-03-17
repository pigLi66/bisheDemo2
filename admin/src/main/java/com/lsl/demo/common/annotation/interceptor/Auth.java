package com.lsl.demo.common.annotation.interceptor;

import com.lsl.demo.common.enums.Actor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户权限控制注解
 * @author lsl
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

    String[] users() default {};

    Actor type() default Actor.ALL;

}
