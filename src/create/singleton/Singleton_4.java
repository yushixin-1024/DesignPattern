package create.singleton;

/**
 * <b>单例模式-懒汉式(线程安全)</b>
 * <ol>
 *     <b>实现步骤:</b>
 *     <li>构造器私有化</li>
 *     <li>向外部暴露一个静态的公共方法,方法修饰为synchronized,保证线程安全</li>
 *     <li>instance==null时进行实例化</li>
 * </ol>
 * <ol>
 *     <b>优点:</b>
 *     <li>达到了懒加载的目的</li>
 *     <li>解决了线程不安全问题</li>
 * </ol>
 * <ol>
 *     <b>缺点:</b>
 *     <li>效率太低</li>
 * </ol>
 * <ol>
 *     <b>结论:</b>
 *     <li>效率太低,不推荐使用</li>
 * </ol>
 */
public class Singleton_4 {

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

        // 2.向外部暴露一个静态的公共方法,方法修饰为synchronized,保证线程安全
        public static synchronized Singleton getInstance() {
            // 3.instance == null时进行实例化
            if ( instance == null ) {
                // 该代码执行一次就可以了,后面获取实例直接return,而synchronized修饰后,所有的都会串行化执行,导致效率降低
                instance = new Singleton();
            }
            return instance;
        }
    }
}
