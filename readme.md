### 一、设计模式的目的:
````
1.代码重用性
2.可读性
3.可扩展性
4.可靠性
5.高内聚,低耦合
````

### 二、设计模式常用的七大原则:
````
1.单一职责原则:
    一个类应该只负责一项职责(类级别,方法级别)
2.接口隔离原则:
    一个类对另一个类的依赖应该建立在最小的接口上
3.依赖倒置原则:
    低层模块尽量都要有抽象类或接口,或者都有,程序稳定性更好;
    变量的声明类型尽量是抽象类或接口,这样我们的变量引用和实际对象间就存在一个缓冲层,利于程序扩展和优化
4.里式替换原则:
    继承时,子类尽量不去覆写父类的方法
5.开闭原则:
    编程中最基础、最重要的设计原则;
    一个软件实体(类、模块、函数)应该对提供方扩展开发,对使用方修改关闭,用抽象构建框架,用实现扩展细节;
    当软件需要变化时,尽量通过扩展软件实体来实现变化,而不是通过修改原有代码来实现
6.迪米特法则:
    最少知道原则,降低类之间的耦合(松耦合),只与"直接的朋友"(成员变量、方法参数、方法返回值)通信,陌生的类不要以局部变量的形式出现在类的内部
7.合成复用原则:
    尽量使用合成/聚合的方式,而不是使用继承
````

### 三、UML(统一建模语言)类图:
````
类、接口、依赖、泛化(继承)、实现、组合、聚合等
类图表示类与类之间的关系:
依赖(Dependence):
    类中用到了对方,就存在了依赖关系
泛化(Generalization):
    即继承关系,依赖关系的特例
实现(Realization):
    实现接口,依赖关系的特例
关联(Association):
    类与类之间的联系,依赖关系的特例;关联具有导航性(单向/双向)
聚合(Aggregation):
    整体和部分的关系(整体和部分可以分开,Computor/Mouse),关联关系的特例;具有导航性和多重性
组合(Composite):
    整体和部分的关系(整体和部分不可以分开,Person/Head)
````

### 四、设计模式(Design Pattern)  软件设计最佳实践  GOF
````
创建型模式:
    单例模式
    简单工厂模式
    抽象工厂模式
    工厂模式
    原型模式
    建造者模式
结构型模式:
    适配器模式
    桥接模式
    装饰模式
    组合模式
    外观模式
    享元模式
    代理模式
行为型模式:
    模板方法模式
    命令模式
    访问者模式
    迭代器模式
    观察者模式
    中介者模式
    备忘录模式
    解释器(Interpreter)模式
    状态模式
    策略模式
    职责链模式
````