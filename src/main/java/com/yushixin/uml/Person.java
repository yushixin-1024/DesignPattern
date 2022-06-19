package com.yushixin.uml;

// 人
public class Person {

    // 性别
    public String sex;

    // 生日
    protected String birth;

    // 工作
    String work;

    // 年龄
    private short age;

    public String getSex() {
        return sex;
    }

    protected String getBirth() {
        return birth;
    }

    String getWork() {
        return work;
    }

    private short getAge() {
        return age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    protected void setBirth(String birth) {
        this.birth = birth;
    }

    void setWork(String work) {
        this.work = work;
    }

    private void setAge(short age) {
        this.age = age;
    }
}
