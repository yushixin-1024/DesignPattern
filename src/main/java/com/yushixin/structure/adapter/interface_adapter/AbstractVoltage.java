package com.yushixin.structure.adapter.interface_adapter;

/**
 * 抽象电压,默认实现
 */
public class AbstractVoltage implements IVoltage {

    @Override
    public int output5V() {
        return 0;
    }

    @Override
    public int output220V() {
        return 0;
    }
}
