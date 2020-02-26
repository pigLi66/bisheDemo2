package com.lsl.demo.first.utils.enums;

/**
 * @Author lisiliang
 */

public enum Operation {
    SUCCESS("操作成功"), FAIL("操作失败");
    Operation(String elem) {
        this.elem = elem;
    }

    public String get() {
        return elem;
    }

    private final String elem;

}
