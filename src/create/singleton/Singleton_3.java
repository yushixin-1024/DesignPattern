package create.singleton;

/**
 * <b>单例模式-懒汉式(线程不安全)</b>
 * <ol>
 *     <b>实现步骤:</b>
 *     <li>构造器私有化</li>
 *     <li>向外部暴露一个静态的公共方法</li>
 *     <li>instance==null时进行实例化</li>
 * </ol>
 * <ol>
 *     <b>优点:</b>
 *     <li>达到了懒加载的目的</li>
 * </ol>
 * <ol>
 *     <b>缺点:</b>
 *     <li>线程不安全</li>
 * </ol>
 * <ol>
 *     <b>结论:</b>
 *     <li>不能使用</li>
 * </ol>
 */
public class Singleton_3 {

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
                // new Singleton()不是一个原子操作,JVM中会进行大致[创建对象-分配内存-对象初始化]等过程,在这之前instance都为null
                // 多线程情况下,多个线程同时执行到该位置,线程获取到时间片后会继续执行,就可能创建多个实例
                instance = new Singleton();
            }
            return instance;
        }
    }
}
