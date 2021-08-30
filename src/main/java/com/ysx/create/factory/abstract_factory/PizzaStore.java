package com.ysx.create.factory.abstract_factory;

/**
 * 客户端:披萨商店
 */
public class PizzaStore {

    public static void main(String[] args) {
        // 此处可以自定义输入城市
        // new OrderPizza(new BeijingFactory());
        new OrderPizza(new ShanghaiFactory());
    }
}
