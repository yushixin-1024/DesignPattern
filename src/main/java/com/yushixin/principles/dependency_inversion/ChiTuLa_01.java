package com.yushixin.principles.dependency_inversion;

public class ChiTuLa_01 {

    public static void main(String[] args) {
        Person person = new Person();
        person.payment( new Ali() );
        person.payment( new WeChat() );
        person.payment( new BankCard() );
    }

    static class Person {
        // 使用支付宝付款
        public void payment(Ali type) {
            System.out.println( type.pay() );
        }
        // 使用微信付款
        public void payment(WeChat type) {
            System.out.println( type.pay() );
        }
        // 使用银行卡付款
        public void payment(BankCard type) {
            System.out.println( type.pay() );
        }
    }

    static class Ali {
        public String pay() {
            return "通过 -支付宝- 付款";
        }
    }

    static class WeChat {
        public String pay() {
            return "通过 -微信- 付款";
        }
    }

    static class BankCard {
        public String pay() {
            return "通过 -银行卡- 付款";
        }
    }
}
