package com.ysx.create.factory.simple_factory.pizza;

/**
 * B披萨
 */
public class BPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("准备制作B披萨的原材料");
    }
}
