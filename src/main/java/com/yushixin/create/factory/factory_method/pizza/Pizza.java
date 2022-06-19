package com.yushixin.create.factory.factory_method.pizza;

/**
 * <b>披萨抽象类-模拟披萨的制作流程</b>
 * <ol>
 *     <li>准备材料</li>
 *     <li>烘焙</li>
 *     <li>切割</li>
 *     <li>装箱</li>
 * </ol>
 */
public abstract class Pizza {

    protected String name;

    /**
     * 1.准备原材料
     * <pre>不同的披萨需要材料不同,交给具体子类实现</pre>
     */
    public abstract void prepare();

    /**
     * 2.烘焙
     */
    public void bake() {
        System.out.println(name + ",正在烘焙中");
    }

    /**
     * 3.切割
     */
    public void cut() {
        System.out.println(name + ",正在切割中");
    }

    /**
     * 4.装箱
     */
    public void box() {
        System.out.println(name + ",正在装箱中");
    }

    /**
     * 设置披萨名称
     */
    public void setName(String name) {
        this.name = name;
    }
}
