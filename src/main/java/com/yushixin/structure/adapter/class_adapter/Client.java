package com.yushixin.structure.adapter.class_adapter;

/**
 * 客户端
 */
public class Client {

    public static void main(String[] args) {
        new Phone().charging(new VoltageAdapter());
    }
}
