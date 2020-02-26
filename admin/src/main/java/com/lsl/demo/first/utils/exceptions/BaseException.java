package com.lsl.demo.first.utils.exceptions;

import lombok.Data;

/**
 * @author lisiliang
 * @since 2020/2/12
 * 自定义异常的基础类
 */

@Data
public class BaseException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private int code;

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }

}
