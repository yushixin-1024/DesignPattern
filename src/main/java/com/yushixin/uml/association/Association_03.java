package com.yushixin.uml.association;

import java.util.List;

// 单向多对一关联
public class Association_03 {

    // 人
    static class Person {
        private List<BankCard> bankCardList;
    }

    // 银行卡
    static class BankCard {
    }
}
