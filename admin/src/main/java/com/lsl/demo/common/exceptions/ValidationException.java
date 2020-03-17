package com.lsl.demo.common.exceptions;

import lombok.Data;
import org.apache.commons.httpclient.HttpStatus;

/**
 * @author lisiliang
 * @since 2020/1/11
 * 用户校验异常类
 */
@Data
public class ValidationException extends BaseException {

    public ValidationException(String message) {
        super(message, HttpStatus.SC_BAD_REQUEST);
    }

    public ValidationException(String message, int code) {
        super(message, code);
    }

}
