package com.yushixin.uml;

// 接收方
public abstract class Receiver {

    // 消息
    private String message;

    // 获取接收类型
    protected abstract String getType();

    public String getMessage() {
        return message;
    }
}
