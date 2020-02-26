package com.lsl.pachong.exceptions;

/**
 * @author lisiliang
 * @since 2020/2/25
 */
public class ResolverException extends RuntimeException {

    public ResolverException(String msg) {
        super(msg);
    }

    public ResolverException(String msg, Throwable e) {
        super(msg, e);
    }

    public ResolverException(Throwable e) {
        super(e);
    }

}
