package com.lsl.demo.common.annotation.aop;

import java.lang.annotation.*;

/**
 * @author lsl
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTest {

    String value() default "mytest.class";

}
