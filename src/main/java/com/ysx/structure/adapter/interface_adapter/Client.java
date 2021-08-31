package com.ysx.structure.adapter.interface_adapter;

/**
 * 客户端
 */
public class Client {

    public static void main(String[] args) {
        new Phone().charging(new AbstractVoltage() {
            @Override
            public int output5V() {
                return 5;
            }
        });
    }
}
