package com.lsl.demo.first.utils.exceptions;

import org.apache.commons.httpclient.HttpStatus;

/**
 * @author lisiliang
 * @since 2020/3/5
 */
    public class UnLoginException extends BaseException {

    public UnLoginException() {
        this("请登陆后执行操作");
    }

    public UnLoginException(String message) {
        super(message, HttpStatus.SC_UNAUTHORIZED);
    }

}
