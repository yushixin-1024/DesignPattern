package com.yushixin.create.factory.factory_method;

import com.yushixin.create.factory.factory_method.pizza.Pizza;
import com.yushixin.utils.Util;

/**
 * 披萨订单
 */
public abstract class OrderPizza {

    /**
     * 创建Pizza抽象方法
     * @param name Pizza名称
     */
    abstract Pizza createPizza(String name);

    /**
     * 构造器
     */
    public OrderPizza() {
        String name;
        Pizza pizza;
        while (true) {
            name = Util.getName();
            // 调用抽象方法,实际调用子类中的具体实现
            pizza = createPizza(name);
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
}
