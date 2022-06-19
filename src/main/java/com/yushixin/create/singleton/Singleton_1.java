package com.yushixin.create.singleton;

/**
 * <b>单例模式-饿汉式(静态常量)</b>
 * <ol>
 *     <b>实现步骤:</b>
 *     <li>构造器私有化</li>
 *     <li>类的内部创建对象</li>
 *     <li>向外部暴露一个静态的公共方法</li>
 * </ol>
 * <ol>
 *     <b>优点:</b>
 *     <li>实现简单,在类加载的时候就完成了实例化</li>
 *     <li>避免了线程同步问题</li>
 * </ol>
 * <ol>
 *     <b>缺点:</b>
 *     <li>没有达到懒加载的目的,如果始终未使用到这个类,会造成内存的浪费</li>
 * </ol>
 * <ol>
 *     <b>结论:</b>
 *     <li><b>可能</b>会造成内存的浪费,所以如果你确定一定会用到这个实例,可以使用这种方式</li>
 * </ol>
 */
public class Singleton_1 {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance1 == instance2);
    }

    static class Singleton {
        // 1.构造器私有化,其他类不能new
        private Singleton() {}

        // 2.类的内部创建对象
        private final static Singleton instance = new Singleton();

        // 3.向外部暴露一个静态的公共方法
        public static Singleton getInstance() {
            return instance;
        }
    }
}
