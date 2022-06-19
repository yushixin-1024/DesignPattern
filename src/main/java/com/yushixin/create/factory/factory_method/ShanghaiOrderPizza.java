package com.yushixin.create.factory.factory_method;

import com.yushixin.create.factory.factory_method.pizza.*;

/**
 * 上海披萨订单
 */
public class ShanghaiOrderPizza extends OrderPizza {

    @Override
    Pizza createPizza(String name) {
        Pizza pizza = null;
        if ( "A".equalsIgnoreCase(name) ) {
            pizza = new ShanghaiAPizza();
            pizza.setName(name);
        } else if ( "B".equalsIgnoreCase(name) ) {
            pizza = new ShanghaiBPizza();
            pizza.setName(name);
        }
        return pizza;
    }
}
