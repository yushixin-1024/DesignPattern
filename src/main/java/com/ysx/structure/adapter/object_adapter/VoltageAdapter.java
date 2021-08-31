package com.ysx.structure.adapter.object_adapter;

/**
 * 电压适配器
 */
public class VoltageAdapter implements IVoltage5V {

    private final Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int i = voltage220V.output220V() / 44;
        System.out.println("输出" + i + "V电压");
        return i;
    }
}
