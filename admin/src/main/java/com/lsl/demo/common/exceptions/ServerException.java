package com.lsl.demo.common.exceptions;

import org.apache.commons.httpclient.HttpStatus;

/**
 * @author lisiliang
 * @since 2020/3/21
 */
public class ServerException extends BaseException {

    public ServerException(String msg, int code) {
        super(msg, code);
    }

    public ServerException(String msg) {
        this(msg, HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    public ServerException() {
        this(HttpStatus.getStatusText(HttpStatus.SC_INTERNAL_SERVER_ERROR));
    }

}
