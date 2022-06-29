package com.yushixin.principles.liskov_substitution;

public class Liskov_02 {

    public static void main(String[] args) {
        Parent parent1 = new Parent();
        parent1.add(1, 2);
        parent1.subtract(1, 2);
        Son son = new Son();
        son.add(1, 2);
        // 减法运算:Parent
        son.subtract1(1, 2);
        // 减法运算:Son
        son.subtract2(1, 2);
        son.multiply(1, 2);
    }
    // 更基础的类
    static class Base {
        // 减法运算
        public void subtract(int a, int b) {
            System.out.println("a - b = " + (a - b));
        }
    }
    static class Parent extends Base {
        // 加法运算
        public void add(int a, int b) {
            System.out.println("a + b = " + (a + b));
        }
    }
    static class Son extends Base {
        private final Parent parent = new Parent();
        // 减法运算:Parent
        public void subtract1(int a, int b) {
            // 调用Parent类的subtract方法
            parent.subtract(a, b);
        }
        // 减法运算:Son
        public void subtract2(int a, int b) {
            System.out.println("a - b - 1 = " + (a - b - 1));
        }
        // 加法运算
        public void add(int a, int b) {
            // 调用Parent类的add方法
            parent.subtract(a, b);
        }
        // 乘法运算
        public void multiply(int a, int b) {
            System.out.println("a * b = " + (a * b));
        }
    }
}
