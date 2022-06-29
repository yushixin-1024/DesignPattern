package com.yushixin.principles.demeter;

import java.util.ArrayList;
import java.util.List;

public class Demeter_01 {

    public static void main(String[] args) {
        School school = new School();
        school.selectAllStudent();
        System.out.println("---------------");
        school.selectAllTeacher();
    }

    // 学校类
    static class School {
        // 查询所有学生
        public void selectAllStudent() {
            // Student以局部变量形式出现,不是直接的朋友
            List<Student> students = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                students.add( new Student(String.valueOf(i)) );
            }
            for (Student student : students) {
                System.out.println(student);
            }
        }
        // 查询所有老师
        public void selectAllTeacher() {
            // Teacher以局部变量形式出现,不是直接的朋友
            List<Teacher> teachers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                teachers.add( new Teacher(String.valueOf(i)) );
            }
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        }
    }

    // 学生类
    static class Student {
        private final String name;
        public Student(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "Student: " + name;
        }
    }

    // 老师类
    static class Teacher {
        private final String name;
        public Teacher(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "Teacher: " + name;
        }
    }
}
