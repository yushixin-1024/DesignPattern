package com.yushixin.principles.interface_segregation;

/**
 * 开动前准备
 */
public class KaiDongLa_02 {

    private static final String name = "黄瓜";

    /**
     * 做菜接口_01
     */
    interface Cooking_01 {

        /**
         * 洗菜
         * @param name 菜名
         */
        void wash(String name);

        /**
         * 切菜
         * @param name 菜名
         */
        void cut(String name);
    }

    /**
     * 做菜接口_02
     */
    interface Cooking_02 {

        /**
         * 凉拌
         * @param name 菜名
         */
        void coldMix(String name);
    }

    /**
     * 做菜接口_03
     */
    interface Cooking_03 {

        /**
         * 炒菜
         * @param name 菜名
         */
        void fry(String name);
    }

    /**
     * 做凉菜
     */
    static class CookingCold implements Cooking_01, Cooking_02 {

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
    }

    /**
     * 做热菜
     */
    static class CookingHot implements Cooking_01, Cooking_03 {

        @Override
        public void wash(String name) {
            System.out.println("洗" + name);
        }

        @Override
        public void cut(String name) {
            System.out.println("切" + name);
        }

        @Override
        public void fry(String name) {
            System.out.println("炒" + name);
        }
    }

    /**
     * 凉拌黄瓜
     */
    static class ColdMixCucumber {

        /**
         * 洗黄瓜
         */
        public void wash(Cooking_01 cooking) {
            cooking.wash(name);
        }

        /**
         * 切黄瓜
         */
        public void cut(Cooking_01 cooking) {
            cooking.cut(name);
        }

        /**
         * 凉拌黄瓜
         */
        public void coldMix(Cooking_02 cooking) {
            cooking.coldMix(name);
        }
    }

    /**
     * 炒黄瓜
     */
    static class FryCucumber {

        /**
         * 洗黄瓜
         */
        public void wash(Cooking_01 cooking) {
            cooking.wash(name);
        }

        /**
         * 切黄瓜
         */
        public void cut(Cooking_01 cooking) {
            cooking.cut(name);
        }

        /**
         * 炒黄瓜
         */
        public void fry(Cooking_03 cooking) {
            cooking.fry(name);
        }
    }

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
}
