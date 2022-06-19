### 一、设计模式的本质:
````
面向对象设计原则的实际运用，是对类的封装性、继承性和多态性以及类的关联关系和组合关系的充分理解。
````

### 二、设计模式的目的:
````
1.代码重用性
2.可读性
3.可扩展性
4.可靠性
5.高内聚,低耦合
````

### 三、设计模式常用的七大原则:
````
1.单一职责原则（Single Responsibility Principle, SRP）:
    一个类应该只负责一项职责(类级别,方法级别)
    ①降低类的复杂度
    ②提高类的可读性
    ③提高类的可维护性
    ④降低变更引起的风险
    ⑤通常情况下，我们应当遵守单一职责原则。
        只有逻辑足够简单，才可以在代码级别违反单一职责原则
        只有类中方法数量足够少，才可以在方法级别保持单一职责原则
2.接口隔离原则（Interface Segregation Principle，ISP）:
    一个类对另一个类的依赖应该建立在最小的接口上
    ①提高系统的灵活性和可维护性
    ②降低系统的耦合性
    ③保证系统的稳定性
    ④使用多个专门的接口还能够体现对象的层次
    ⑤能减少项目工程中的代码冗余
3.依赖倒置原则Dependency Inversion Principle，DIP）:
    低层模块尽量都要有抽象类或接口,或者都有,程序稳定性更好;
    变量的声明类型尽量是抽象类或接口,这样我们的变量引用和实际对象间就存在一个缓冲层,利于程序扩展和优化
    ①可以降低类间的耦合性
    ②可以提高系统的稳定性
    ③可以减少并行开发引起的风险
    ④可以提高代码的可读性和可维护性
4.里式替换原则（Liskov Substitution Principle，LSP）:
    继承时,子类尽量不去覆写父类的方法
    ①里氏替换原则是实现开闭原则的重要方式之一
    ②它克服了继承中重写父类造成的可复用性变差的缺点
    ③它是动作正确性的保证。即类的扩展不会给已有的系统引入新的错误，降低代码出错的可能性
    ④加强程序的健壮性，同时变更时可以做到非常好的兼容性，提高程序的维护性、可扩展性，降低需求变更时引入的风险
5.开闭原则（Open Closed Principle，OCP）:
    编程中最基础、最重要的设计原则;
    一个软件实体(类、模块、函数)应该对提供方扩展开发,对使用方修改关闭,用抽象构建框架,用实现扩展细节;
    当软件需要变化时,尽量通过扩展软件实体来实现变化,而不是通过修改原有代码来实现
    ①对软件测试的影响
        软件遵守开闭原则的话，软件测试时只需要对扩展的代码进行测试就可以，因为原有的测试代码仍然能够正常运行。
    ②可以提高代码的可复用性
        粒度越小，被复用的可能性就越大；在面向对象的程序设计中，根据原子和抽象编程可以提高代码的可复用性。
    ③可以提高软件的可维护性
        遵守开闭原则的软件，其稳定性高和延续性强，从而易于扩展和维护。
6.迪米特法则（Law of Demeter，LOD）/最少知识原则（Least Knowledge Principle，LKP）:
    降低类之间的耦合(松耦合),只与"直接的朋友"通信,陌生的类不要以局部变量的形式出现在类的内部
    "直接的朋友": 该类的成员变量、方法参数、方法返回值中的类
    ①提高模块的相对独立性，降低类之间的耦合度
    ②亲合度降低，从而提高了类的可复用率和系统的扩展性
7.合成/聚合复用原则（Composite/Aggregate Reuse Principle，C/ARP）:
    尽量使用合成/聚合的方式,而不是使用继承
    ①维持类的封装性
    ②新旧类之间的耦合度低
    ③复用的灵活性高
````

### 四、UML(统一建模语言)类图:
````
类、接口、依赖、泛化(继承)、实现、组合、聚合等
类图表示类与类之间的关系:
依赖(Dependence):
    类中用到了对方,就存在了依赖关系
    1.类的成员属性
    2.方法参数
    3.方法返回值
    4.方法体中使用到
泛化(Generalization):
    即继承关系,依赖关系的特例
实现(Realization):
    实现接口,依赖关系的特例
关联(Association):
    类与类之间的联系,依赖关系的特例;关联具有导航性(单向/双向)
聚合(Aggregation):
    整体和部分的关系(整体和部分可以分开,Computor/Mouse),关联关系的特例;具有导航性和多重性
组合(Composite):
    整体和部分的关系(整体和部分不可以分开,Person/Head),关联关系的特例;具有导航性和多重性
````

### 五、设计模式(Design Pattern)  软件设计最佳实践  GOF
````
创建型模式:
    单例模式 (yushixin.create.singleton)
    简单工厂模式 (yushixin.create.simple_factory)
    工厂方法模式 (yushixin.create.factory_method)
    抽象工厂模式 (yushixin.create.abstract_factory)
    原型模式 (yushixin.create.prototype)
    建造者模式 (yushixin.create.)
结构型模式:
    适配器模式 (yushixin.structure.)
    桥接模式 (yushixin.structure.)
    装饰模式 (yushixin.structure.)
    组合模式 (yushixin.structure.)
    外观模式 (yushixin.structure.)
    享元模式 (yushixin.structure.)
    代理模式 (yushixin.structure.)
行为型模式:
    模板方法模式 (yushixin.behavior.)
    命令模式 (yushixin.behavior.)
    访问者模式 (yushixin.behavior.)
    迭代器模式 (yushixin.behavior.)
    观察者模式 (yushixin.behavior.)
    中介者模式 (yushixin.behavior.)
    备忘录模式 (yushixin.behavior.)
    解释器(Interpreter)模式 (yushixin.behavior.)
    状态模式 (yushixin.behavior.)
    策略模式 (yushixin.behavior.)
    职责链模式 (yushixin.behavior.)
````

````
每种设计模式都有对应的UML类图,需要在IDEA中安装PlantUML integration插件
````
![image](https://user-images.githubusercontent.com/25497555/131294665-629e3923-90f1-4a55-aa09-45992571316d.png)
![image](https://user-images.githubusercontent.com/25497555/131294969-92fe557d-02f0-4dcf-9d0d-e07ad00a3517.png)
