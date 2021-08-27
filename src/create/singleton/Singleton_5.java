package create.singleton;

/**
 * <b>单例模式-懒汉式(线程安全,同步代码块)</b>
 * <ol>
 *     <b>实现步骤:</b>
 *     <li>构造器私有化</li>
 *     <li>向外部暴露一个静态的公共方法</li>
 *     <li>instance==null时进行实例化</li>
 *     <li>同步代码块,不保证线程安全</li>
 * </ol>
 * <ol>
 *     <b>优点:</b>
 *     <li>本意是对Singleton_4的改进,但是很遗憾(算是优点吧)</li>
 * </ol>
 * <ol>
 *     <b>缺点:</b>
 *     <li>不能保证线程安全</li>
 * </ol>
 * <ol>
 *     <b>结论:</b>
 *     <li>不能使用</li>
 * </ol>
 */
public class Singleton_5 {

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

        private static Singleton instance;

        // 2.向外部暴露一个静态的公共方法
        public static Singleton getInstance() {
            // 3.instance == null时进行实例化
            if ( instance == null ) {
                // 4.同步代码块,但是多线程时,仍然不能保证线程安全(多个线程有可能都进入到这里,synchronized没有意义)
                synchronized (Singleton.class) {
                    instance = new Singleton();
                }
            }
            return instance;
        }
    }
}
