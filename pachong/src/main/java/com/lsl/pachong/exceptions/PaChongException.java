package com.lsl.pachong.exceptions;

/**
 * @author lisiliang
 * @since 2020/2/25
 */
public class PaChongException extends RuntimeException {

    public PaChongException(String msg) {
        super(msg);
    }

    public PaChongException(String msg, Throwable e) {
        super(msg, e);
    }

    public PaChongException(Throwable e) {
        super(e);
    }

}
