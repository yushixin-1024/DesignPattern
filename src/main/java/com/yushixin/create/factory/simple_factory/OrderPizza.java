package com.yushixin.create.factory.simple_factory;

import com.yushixin.create.factory.simple_factory.pizza.Pizza;
import com.yushixin.utils.Util;

/**
 * 披萨订单
 */
public class OrderPizza {

    /**
     * 构造器
     */
    public OrderPizza() {
        make();
    }

    /**
     * 制作披萨
     */
    public void make() {
        String name;
        Pizza pizza;
        while (true) {
            name = Util.getName();
            pizza = SimpleFactory.createPizza(name);
            if ( pizza == null ) {
                System.out.println("订购披萨失败");
                return;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
    }
}
