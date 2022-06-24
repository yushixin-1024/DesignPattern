package com.yushixin.principles.composite_reuse;

/**
 * 依赖关系
 */
public class Demo_04 {

    public static void main(String[] args) {
        B b = new B();
        b.test( new A() );
    }

    static class A {
        public void test() {
            System.out.println("A 类的 test 方法...");
        }
    }

    static class B {

        public void test(A a) {
            // A类方法
            a.test();
            // B类对A类方法进行增强
            System.out.println("B 类增强后的 test 方法...");
        }
    }
}
