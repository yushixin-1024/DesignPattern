package create.singleton;

/**
 * <b>单例模式-静态内部类</b>
 * <ol>
 *     <b>实现步骤:</b>
 *     <li>构造器私有化</li>
 *     <li>静态内部类,Singleton类加载的时候不会加载内部类,只有用到内部类时才回去加载内部类</li>
 *     <li>向外部暴露一个静态的公共方法</li>
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
public class Singleton_7 {

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

        // 2.静态内部类,Singleton类加载的时候不会加载内部类,只有用到内部类时才回去加载内部类(保证懒加载)
        private static class SingletonInstance {
            private static final Singleton instance = new Singleton();
        }

        // 3.向外部暴露一个静态的公共方法,此时会装载SingletonInstance,类装载时是线程安全的(保证线程安全)
        public static Singleton getInstance() {
            return SingletonInstance.instance;
        }
    }
}
