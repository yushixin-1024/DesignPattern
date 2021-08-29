package com.ysx.create.simple_factory.order;

import com.ysx.create.simple_factory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        String name = null;
        Pizza pizza = null;
        while (true) {
            name  = getName();
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

    /**
     * 获取披萨名称
     */
    private String getName() {
        System.out.println("输入披萨名称:");
        String name = "";
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            name = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
