package com.yushixin.principles.single_responsibility;

/**
 * IT部门
 * 该部门下有三个员工
 * 开发: Developer
 * 测试: Tester
 * 运维: Operator
 */
public class ITDepartment_03 {
    public static void main(String[] args) {
        Employee.workCode("Developer");
        Employee.workUseCase("Tester");
        Employee.workScript("Operator");
    }
    // 员工类
    static class Employee {
        public static void workCode(String name) {
            System.out.println(name + "正在写代码...");
        }
        public static void workUseCase(String name) {
            System.out.println(name + "正在写用例...");
        }
        public static void workScript(String name) {
            System.out.println(name + "正在写脚本...");
        }
    }
}
