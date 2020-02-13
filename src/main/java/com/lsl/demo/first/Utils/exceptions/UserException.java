package com.lsl.demo.first.Utils.exceptions;

import org.apache.commons.httpclient.HttpStatus;

/**
 * @author lisiliang
 * @since 2020/2/12
 * 用户相关异常类，如用户名重复，密码错误等
 */
public class UserException extends BaseException{

    public UserException(String message) {
        super(message, HttpStatus.SC_BAD_REQUEST);
    }

    public UserException(String message, int code) {
        super(message, code);
    }

}
