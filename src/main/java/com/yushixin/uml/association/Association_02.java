package com.yushixin.uml.association;

// 双向一对一关联
public class Association_02 {

    // 人
    static class Person {
        private IDCard idCard;
    }

    // 身份证
    static class IDCard {
        private Person person;
    }
}
