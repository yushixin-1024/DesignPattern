package com.ysx.create.factory.simple_factory;

import com.ysx.create.factory.simple_factory.pizza.APizza;
import com.ysx.create.factory.simple_factory.pizza.BPizza;
import com.ysx.create.factory.simple_factory.pizza.CPizza;
import com.ysx.create.factory.simple_factory.pizza.Pizza;

/**
 * 订购披萨-简单(静态)工厂类
 */
public class SimpleFactory {

    /**
     * 生成一个披萨
     */
    public static Pizza createPizza(String name) {
        Pizza pizza = null;
        switch ( name ) {
            case "A":case "a":
                pizza = new APizza();
                pizza.setName(name);
                break;
            case "B":case "b":
                pizza = new BPizza();
                pizza.setName(name);
                break;
            case "C":case "c":
                pizza = new CPizza();
                pizza.setName(name);
                break;
            default:
        }
        return pizza;
    }
}
