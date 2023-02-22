package com.accendl.azeroth.service;

import io.netty.util.concurrent.CompleteFuture;
import io.netty.util.concurrent.EventExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.Callable;

public class HttpContentFuture<String> extends CompleteFuture {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Creates a new instance.
     *
     * @param executor the {@link EventExecutor} associated with this future
     */
    public HttpContentFuture(EventExecutor executor) {
        super(executor);
    }

    @Override
    public boolean isSuccess() {
        if (StringUtils.hasText(this.content.toString())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Throwable cause() {
        return null;
    }

    @Override
    public Object getNow() {
        return this.content;
    }

}
