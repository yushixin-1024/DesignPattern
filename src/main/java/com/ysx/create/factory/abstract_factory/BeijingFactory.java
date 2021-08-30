package com.ysx.create.factory.abstract_factory;

import com.ysx.create.factory.abstract_factory.pizza.BeijingAPizza;
import com.ysx.create.factory.abstract_factory.pizza.BeijingBPizza;
import com.ysx.create.factory.abstract_factory.pizza.Pizza;

/**
 * 北京披萨工厂
 */
public class BeijingFactory extends AbstractFactory {

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
