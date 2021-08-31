package com.ysx.structure.adapter.interface_adapter;

/**
 * 手机
 */
public class Phone {

    /**
     * 充电
     */
    public void charging(AbstractVoltage voltage) {
        System.out.println("使用接口适配器");
        int i = voltage.output5V();
        if ( i == 5 ) {
            System.out.println("电压为5V,可以充电");
        } else {
            System.out.println("不可以充电");
        }
    }
}
