package com.tct.springboot.exceptions;

public class NullPropertyException extends NullPointerException {
    public NullPropertyException(String s) {
        super(s);
    }
}
