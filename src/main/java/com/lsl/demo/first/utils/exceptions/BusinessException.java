package com.lsl.demo.first.utils.exceptions;

import org.apache.commons.httpclient.HttpStatus;

/**
 * @author lisiliang
 * @since 2020/2/14
 */
public class BusinessException extends BaseException {

    public BusinessException(String message) {
        super(message, HttpStatus.SC_BAD_REQUEST);
    }

    public BusinessException(String message, int code) {
        super(message, code);
    }

}
