package com.ysx.create.simple_factory.pizza;

/**
 * A披萨
 */
public class APizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("准备制作A披萨的原材料");
    }
}
