package com.yushixin.principles.interface_segregation;

/**
 * 开动前准备
 */
public class KaiDongLa_01 {
    private static final String name = "黄瓜";
    public static void main(String[] args) {
        CookingCold cookingCold = new CookingCold();
        ColdMixCucumber coldMixCucumber = new ColdMixCucumber();
        coldMixCucumber.wash(cookingCold);
        coldMixCucumber.cut(cookingCold);
        coldMixCucumber.coldMix(cookingCold);
        System.out.println();
        CookingHot cookingHot = new CookingHot();
        FryCucumber fryCucumber = new FryCucumber();
        fryCucumber.wash(cookingHot);
        fryCucumber.cut(cookingHot);
        fryCucumber.fry(cookingHot);;
    }
    // 做菜接口
    interface Cooking {
        // 洗菜
        void wash(String name);
        // 切菜
        void cut(String name);
        // 凉拌
        void coldMix(String name);
        // 炒菜
        void fry(String name);
    }
    // 做凉菜
    static class CookingCold implements Cooking {
        @Override
        public void wash(String name) {
            System.out.println("洗" + name);
        }
        @Override
        public void cut(String name) {
            System.out.println("切" + name);
        }
        @Override
        public void coldMix(String name) {
            System.out.println("凉拌" + name);
        }
        @Override
        public void fry(String name) {}
    }
    // 做热菜
    static class CookingHot implements Cooking {
        @Override
        public void wash(String name) {
            System.out.println("洗" + name);
        }
        @Override
        public void cut(String name) {
            System.out.println("切" + name);
        }
        @Override
        public void coldMix(String name) {}
        @Override
        public void fry(String name) {
            System.out.println("炒" + name);
        }
    }
    // 凉拌黄瓜
    static class ColdMixCucumber {
        // 洗黄瓜
        public void wash(Cooking cooking) {
            cooking.wash(name);
        }
        // 切黄瓜
        public void cut(Cooking cooking) {
            cooking.cut(name);
        }
        // 凉拌黄瓜
        public void coldMix(Cooking cooking) {
            cooking.coldMix(name);
        }
    }
    // 炒黄瓜
    static class FryCucumber {
        // 洗黄瓜
        public void wash(Cooking cooking) {
            cooking.wash(name);
        }
        // 切黄瓜
        public void cut(Cooking cooking) {
            cooking.cut(name);
        }
        // 炒黄瓜
        public void fry(Cooking cooking) {
            cooking.fry(name);
        }
    }
}
