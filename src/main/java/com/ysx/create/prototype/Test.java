package com.ysx.create.prototype;

/**
 * 测试类
 */
public class Test {

    public static void main(String[] args) throws CloneNotSupportedException {
        // clone
        Prototype prototype = new Prototype(10, "Prototype");
        Prototype clone = (Prototype) prototype.clone();
        System.out.println("prototype:" + prototype);
        System.out.println("prototype-hashCode:" + prototype.hashCode());
        System.out.println("prototype-getInner-hashCode:" + prototype.getInner().hashCode());
        System.out.println("clone:" + clone.toString());
        System.out.println("clone-hashCode:" + clone.hashCode());
        System.out.println("clone-getInner-hashCode:" + clone.getInner().hashCode());

        // 序列化
        Prototype prototype1 = new Prototype(10, "Prototype");
        Prototype serialize = (Prototype) prototype1.serialize();
        System.out.println("prototype1:" + prototype1);
        System.out.println("prototype1-hashCode:" + prototype1.hashCode());
        System.out.println("prototype1-getInner-hashCode:" + prototype1.getInner().hashCode());
        System.out.println("serialize:" + serialize.toString());
        System.out.println("serialize-hashCode:" + serialize.hashCode());
        System.out.println("serialize-getInner-hashCode:" + serialize.getInner().hashCode());
    }
}
