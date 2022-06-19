package com.yushixin.create.builder;

/**
 * 抽象建造者
 */
public abstract class Builder {

    // 产品
    protected Product product = new Product();

    /**
     * 建造A部分
     */
    public abstract void buildPartA();

    /**
     * 建造B部分
     */
    public abstract void buildPartB();

    /**
     * 建造C部分
     */
    public abstract void buildPartC();
}
