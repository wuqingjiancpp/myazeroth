package com.accendl.web.enums;

public enum ErrorCode {

    OK(0,"正常");

    final int code;
    final String message;
    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
