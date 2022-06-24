package com.yushixin.principles.composite_reuse;

/**
 * 继承关系
 */
public class Demo_01 {

    public static void main(String[] args) {
        B b = new B();
        b.test();
    }

    static class A {
        public void test() {
            System.out.println("A 类的 test 方法...");
        }
    }

    static class B extends A {
        @Override
        public void test() {
            // A类方法
            super.test();
            // B类对A类方法进行增强
            System.out.println("B 类增强后的 test 方法...");
        }
    }
}
