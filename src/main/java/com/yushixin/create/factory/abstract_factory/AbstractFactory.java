package com.yushixin.create.factory.abstract_factory;

import com.yushixin.create.factory.abstract_factory.pizza.Pizza;

/**
 * 抽象工厂
 */
public abstract class AbstractFactory {

    /**
     * 创建Pizza抽象方法
     * @param name Pizza名称
     */
    abstract Pizza createPizza(String name);
}
