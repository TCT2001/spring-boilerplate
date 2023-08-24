package com.tct.springboot.enums;

public enum StatusCode {
    EXCEPTION(2),
    SUCCESS(1),
    FAIL(0);

    private int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
