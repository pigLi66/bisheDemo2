package com.lsl.demo.common.enums;


/**
 * @author lsl
 */
public enum Actor {

    /**
     * 管理员
     */
    ADMIN("0"),
    /**
     * 普通用户
     */
    USER("1"),
    /**
     * 所有用户
     */
    ALL("all");

    private String type;

    Actor(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
