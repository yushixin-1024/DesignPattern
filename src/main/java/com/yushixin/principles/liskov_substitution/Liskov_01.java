package com.yushixin.principles.liskov_substitution;

public class Liskov_01 {

    public static void main(String[] args) {
        Parent parent1 = new Parent();
        parent1.add(1, 2);
        parent1.subtract(1, 2);
        Son son = new Son();
        son.add(1, 2);
        son.subtract(1, 2);
    }

    static class Parent {
        // 加法运算
        public void add(int a, int b) {
            System.out.println("a + b = " + (a + b));
        }
        // 减法运算
        public void subtract(int a, int b) {
            System.out.println("a - b = " + (a - b));
        }
    }

    static class Son extends Parent {
        // 减法运算
        @Override
        public void subtract(int a, int b) {
            System.out.println("a * b = " + (a * b));
        }
    }
}
