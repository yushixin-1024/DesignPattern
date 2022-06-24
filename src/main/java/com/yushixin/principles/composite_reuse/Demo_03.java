package com.yushixin.principles.composite_reuse;

/**
 * 聚合关系
 */
public class Demo_03 {

    public static void main(String[] args) {
        B b = new B();
        b.setA( new A() );
        b.test();
    }

    static class A {
        public void test() {
            System.out.println("A 类的 test 方法...");
        }
    }

    static class B {

        private A a;

        public void test() {
            // A类方法
            a.test();
            // B类对A类方法进行增强
            System.out.println("B 类增强后的 test 方法...");
        }

        public void setA(A a) {
            this.a = a;
        }
    }
}
