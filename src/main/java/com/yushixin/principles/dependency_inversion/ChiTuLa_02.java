package com.yushixin.principles.dependency_inversion;

public class ChiTuLa_02 {

    public static void main(String[] args) {
        Person person = new Person();
        person.payment( new Ali() );
        person.payment( new WeChat() );
        person.payment( new BankCard() );
    }

    static class Person {
        // 使用支付宝付款
        public void payment(IPayment type) {
            System.out.println( type.pay() );
        }
    }

    // 付款接口
    interface IPayment {
        String pay();
    }

    static class Ali implements IPayment {
        @Override
        public String pay() {
            return "通过 -支付宝- 付款";
        }
    }

    static class WeChat implements IPayment {
        @Override
        public String pay() {
            return "通过 -微信- 付款";
        }
    }

    static class BankCard implements IPayment {
        @Override
        public String pay() {
            return "通过 -银行卡- 付款";
        }
    }
}
