package com.yushixin.create.factory.simple_factory;

/**
 * 客户端:披萨商店,下订单
 */
public class PizzaStore {

    public static void main(String[] args) {
        OrderPizza order = new OrderPizza();
        order.make();
        System.out.println("退出订购程序");
    }
}
