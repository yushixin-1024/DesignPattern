package com.yushixin.structure.adapter.class_adapter;

/**
 * 电压适配器
 */
public class VoltageAdapter extends Voltage220V implements IVoltage5V {

    @Override
    public int output5V() {
        int i = super.output220V() / 44;
        System.out.println("输出" + i + "V电压");
        return i;
    }
}
