package com.lsl.demo.first.utils.enums;

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
