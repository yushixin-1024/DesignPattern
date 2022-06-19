package com.yushixin.principles.single_responsibility;

/**
 * IT部门
 * 该部门下有三个员工
 * 开发: Developer
 * 测试: Tester
 * 运维: Operator
 */
public class ITDepartment_01 {
    /**
     * 员工类
     */
    static class Employee {

        public static void work(String name) {
            System.out.println(name + "正在写代码...");
        }
    }
    public static void main(String[] args) {
        Employee.work("Developer");
        Employee.work("Tester");
        Employee.work("Operator");
    }
}
