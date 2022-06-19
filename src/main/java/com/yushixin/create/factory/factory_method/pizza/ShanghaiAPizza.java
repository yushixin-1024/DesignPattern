package com.yushixin.create.factory.factory_method.pizza;

/**
 * 上海A披萨
 */
public class ShanghaiAPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("准备制作上海A披萨的原材料");
    }
}
