package com.ysx.create.builder;

/**
 * 具体建造者1
 */
public class BuilderConcrete1 extends Builder {

    @Override
    public void buildPartA() {
        System.out.println("具体建造者1-建造PartA");
    }

    @Override
    public void buildPartB() {
        System.out.println("具体建造者1-建造PartB");
    }

    @Override
    public void buildPartC() {
        System.out.println("具体建造者1-建造PartC");
    }
}
