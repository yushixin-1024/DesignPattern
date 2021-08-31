package com.ysx.structure.adapter.object_adapter;

/**
 * 手机
 */
public class Phone {

    /**
     * 充电
     */
    public void charging(IVoltage5V iVoltage5V) {
        System.out.println("使用对象适配器");
        int i = iVoltage5V.output5V();
        if ( i == 5 ) {
            System.out.println("电压为5V,可以充电");
        } else {
            System.out.println("不可以充电");
        }
    }
}
