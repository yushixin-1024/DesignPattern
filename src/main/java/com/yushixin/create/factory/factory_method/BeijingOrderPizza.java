package com.yushixin.create.factory.factory_method;

import com.yushixin.create.factory.factory_method.pizza.BeijingAPizza;
import com.yushixin.create.factory.factory_method.pizza.BeijingBPizza;
import com.yushixin.create.factory.factory_method.pizza.Pizza;

/**
 * 北京披萨订单
 */
public class BeijingOrderPizza extends OrderPizza {

    @Override
    Pizza createPizza(String name) {
        Pizza pizza = null;
        if ( "A".equalsIgnoreCase(name) ) {
            pizza = new BeijingAPizza();
            pizza.setName(name);
        } else if ( "B".equalsIgnoreCase(name) ) {
            pizza = new BeijingBPizza();
            pizza.setName(name);
        }
        return pizza;
    }
}
