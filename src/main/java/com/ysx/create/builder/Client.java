package com.ysx.create.builder;

/**
 * 客户端
 */
public class Client {

    public static void main(String[] args) {
        // new Direct(new BuilderConcrete1()).build();
        new Direct(new BuilderConcrete2()).build();
    }
}
