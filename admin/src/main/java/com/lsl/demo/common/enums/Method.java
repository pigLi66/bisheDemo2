package com.lsl.demo.common.enums;

/**
 * @author lsl
 */

public enum Method {
    GET("GET"), PUT("PUT"), POST("POST"), DELETE("DELETE");
    Method(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    private final String value;
}
