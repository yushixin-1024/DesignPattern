package com.yushixin.principles.single_responsibility;

/**
 * IT部门
 * 该部门下有三个员工
 * 开发: Developer
 * 测试: Tester
 * 运维: Operator
 */
public class ITDepartment_02 {
    public static void main(String[] args) {
        Developer.work("Developer");
        Tester.work("Tester");
        Operator.work("Operator");
    }
    // 员工类:开发
    static class Developer {
        public static void work(String name) {
            System.out.println(name + "正在写代码...");
        }
    }
    // 员工类:测试
    static class Tester {
        public static void work(String name) {
            System.out.println(name + "正在写用例...");
        }
    }
    // 员工类:运维
    static class Operator {
        public static void work(String name) {
            System.out.println(name + "正在写脚本...");
        }
    }
}
