package com.lsl.demo.first.Utils.exceptions;

import lombok.Data;
import org.apache.commons.httpclient.HttpStatus;

/**
 * @author lisiliang
 * @since 2020/1/11
 */
@Data
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code = HttpStatus.SC_BAD_REQUEST;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, int code) {
        super(message);
        this.code = code;
    }

}
