package create.singleton;

/**
 * <b>单例模式-枚举</b>
 * <ol>
 *     <b>实现步骤:</b>
 *     <li>创建枚举</li>
 * </ol>
 * <ol>
 *     <b>优点:</b>
 *     <li>避免了线程同步问题</li>
 *     <li>防止反序列化创建对象</li>
 *     <li>Effective Java作者Josh Bloch提倡的方式</li>
 * </ol>
 * <ol>
 *     <b>结论:</b>
 *     <li>推荐使用</li>
 * </ol>
 */
public class Singleton_8 {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.instance;
        Singleton instance2 = Singleton.instance;
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
        System.out.println(instance1 == instance2);
    }

    // 1.创建枚举实现单例
    // enum实际上是extends抽象类java.lang.Enum
    enum Singleton {
        instance
    }
}
