package com.yushixin.principles.demeter;

import java.util.ArrayList;
import java.util.List;

public class Demeter_02 {

    public static void main(String[] args) {
        School school = new School();
        school.printAllStudent( school.selectAllStudent() );
        System.out.println("---------------");
        school.printAllTeacher( school.selectAllTeacher() );
    }

    // 学校类
    static class School {
        // 查询所有学生
        public List<Student> selectAllStudent() {
            // Student以方法返回值形式出现,是直接的朋友
            List<Student> students = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                students.add( new Student(String.valueOf(i)) );
            }
            return students;
        }
        // 打印所有学生
        public void printAllStudent(List<Student> students) {
            // Student方法参数形式出现,是直接的朋友
            for (Student student : students) {
                System.out.println(student);
            }
        }
        // 查询所有老师
        public List<Teacher> selectAllTeacher() {
            // Teacher以方法返回值形式出现,是直接的朋友
            List<Teacher> teachers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                teachers.add( new Teacher(String.valueOf(i)) );
            }
            return teachers;
        }
        // 打印所有老师
        public void printAllTeacher(List<Teacher> teachers) {
            // Teacher方法参数形式出现,是直接的朋友
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
