package com.ysx.create.factory.simple_factory.pizza;

/**
 * C披萨
 */
public class CPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("准备制作C披萨的原材料");
    }
}
