package com.ysx.create.factory.abstract_factory;

import com.ysx.create.factory.abstract_factory.pizza.*;

/**
 * 上海披萨工厂
 */
public class ShanghaiFactory extends AbstractFactory {

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
