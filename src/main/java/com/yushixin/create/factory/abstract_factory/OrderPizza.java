package com.yushixin.create.factory.abstract_factory;

import com.yushixin.create.factory.abstract_factory.pizza.Pizza;
import com.yushixin.utils.Util;

/**
 * 披萨订单
 */
public class OrderPizza {

    public OrderPizza(AbstractFactory factory) {
        while ( true ) {
            Pizza pizza = factory.createPizza(Util.getName());
            if ( pizza == null ) {
                System.out.println("披萨订购失败");
                break;
            }
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
    }
}
