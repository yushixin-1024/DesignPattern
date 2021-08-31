package com.ysx.create.builder;

/**
 * 指挥者
 */
public class Direct {

    // 聚合抽象建造者
    private final Builder builder;

    public Direct(Builder builder) {
        this.builder = builder;
    }

    /**
     * 建造产品
     */
    public void build() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
    }
}
