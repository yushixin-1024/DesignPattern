package com.ysx.create.factory.factory_method.pizza;

/**
 * 上海B披萨
 */
public class ShanghaiBPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("准备制作上海B披萨的原材料");
    }
}
