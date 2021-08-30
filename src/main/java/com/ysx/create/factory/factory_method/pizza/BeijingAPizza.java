package com.ysx.create.factory.factory_method.pizza;

/**
 * 北京A披萨
 */
public class BeijingAPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("准备制作北京A披萨的原材料");
    }
}
