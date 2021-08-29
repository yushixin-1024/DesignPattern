package com.ysx.create.singleton;

/**
 * <b>单例模式-双重检查锁</b>
 * <ol>
 *     <b>实现步骤:</b>
 *     <li>构造器私有化</li>
 *     <li>volatile保证多线程下的可见性</li>
 *     <li>向外部暴露一个静态的公共方法</li>
 *     <li>非空判断</li>
 *     <li>同步代码块</li>
 *     <li>再次非空判断(保证多线程下的单例)</li>
 * </ol>
 * <ol>
 *     <b>优点:</b>
 *     <li>保证了线程安全</li>
 *     <li>保证了懒加载</li>
 *     <li>保证了效率</li>
 * </ol>
 * <ol>
 *     <b>结论:</b>
 *     <li>推荐使用</li>
 * </ol>
 */
public class Singleton_6 {

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

        // 2.volatile保证多线程下的可见性
        private static volatile Singleton instance;

        // 3.向外部暴露一个静态的公共方法
        public static Singleton getInstance() {
            // 3.非空判断
            if ( instance == null ) {
                // 4.同步代码块
                synchronized (Singleton.class) {
                    // 5.再次非空判断(保证多线程下的单例)
                    if ( instance == null ) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}
