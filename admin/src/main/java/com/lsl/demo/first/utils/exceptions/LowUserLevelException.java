package com.lsl.demo.first.utils.exceptions;


import org.apache.commons.httpclient.HttpStatus;

/**
 * @author lisiliang
 * @since 2020/3/7
 */
public class LowUserLevelException extends BaseException{

    public LowUserLevelException() {
        this("用户权限不够");
    }

    public LowUserLevelException(String msg) {
        super(msg, HttpStatus.SC_UNAUTHORIZED);
    }

}
