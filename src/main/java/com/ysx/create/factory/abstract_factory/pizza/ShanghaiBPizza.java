package com.ysx.create.factory.abstract_factory.pizza;

/**
 * 上海B披萨
 */
public class ShanghaiBPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("准备制作上海B披萨的原材料");
    }
}
