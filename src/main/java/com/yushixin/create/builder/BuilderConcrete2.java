package com.yushixin.create.builder;

/**
 * 具体建造者2
 */
public class BuilderConcrete2 extends Builder {

    @Override
    public void buildPartA() {
        System.out.println("具体建造者2-建造PartA");
    }

    @Override
    public void buildPartB() {
        System.out.println("具体建造者2-建造PartB");
    }

    @Override
    public void buildPartC() {
        System.out.println("具体建造者2-建造PartC");
    }
}
