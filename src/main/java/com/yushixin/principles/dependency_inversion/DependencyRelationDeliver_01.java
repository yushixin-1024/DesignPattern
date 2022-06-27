package com.yushixin.principles.dependency_inversion;

/**
 * 依赖关系传递-通过接口传递
 */
public class DependencyRelationDeliver_01 {

    public static void main(String[] args) {
        IComputer computer = new ShineLon();
        ISwitch s = new Switch();
        s.turnOn(computer);
    }

    // 电脑接口
    interface IComputer {
        // 运行
        void play();
    }

    // 神舟(炫龙)笔记本
    static class ShineLon implements IComputer {
        @Override
        public void play() {
            System.out.println("神舟(炫龙)笔记本,正在运行...");
        }
    }

    // 开关接口
    interface ISwitch {
        // 打开
        void turnOn(IComputer computer);
    }

    // 开关实现类
    static class Switch implements ISwitch {
        @Override
        public void turnOn(IComputer computer) {
            computer.play();
        }
    }
}
