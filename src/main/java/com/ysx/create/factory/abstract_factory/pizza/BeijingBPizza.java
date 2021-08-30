package com.ysx.create.factory.abstract_factory.pizza;

/**
 * 北京B披萨
 */
public class BeijingBPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("准备制作北京B披萨的原材料");
    }
}
