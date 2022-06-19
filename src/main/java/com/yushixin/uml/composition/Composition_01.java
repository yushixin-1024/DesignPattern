package com.yushixin.uml.composition;

// 组合关系
public class Composition_01 {
    // 人
    static class Person {
        private IDCard idCard = new IDCard();
    }

    // 身份证
    static class IDCard {
    }
}
