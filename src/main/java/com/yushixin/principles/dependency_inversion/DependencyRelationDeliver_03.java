package com.yushixin.principles.dependency_inversion;

/**
 * 依赖关系传递-通过Setter方法传递
 */
public class DependencyRelationDeliver_03 {
    
    public static void main(String[] args) {
        IComputer computer = new ShineLon();
        Switch s = new Switch();
        s.setComputer(computer);
        s.turnOn();
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
        void turnOn();
    }

    // 开关实现类
    static class Switch implements ISwitch {

        private IComputer computer;

        @Override
        public void turnOn() {
            this.computer.play();
        }

        public void setComputer(IComputer computer) {
            this.computer = computer;
        }
    }
}
