package com.tct.springboot.exceptions;

import com.tct.springboot.enums.StatusCode;

public class CommonException extends RuntimeException {
    public CommonException(String message) {
        super(message);
    }
}
