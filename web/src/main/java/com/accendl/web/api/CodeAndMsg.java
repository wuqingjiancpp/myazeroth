package com.accendl.web.api;

import lombok.Data;

@Data
public class CodeAndMsg<T> {

    private final int code;
    private final String message;
    private T content;

    public CodeAndMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CodeAndMsg(int code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

}
