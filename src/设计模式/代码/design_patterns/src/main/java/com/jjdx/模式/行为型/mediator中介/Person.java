package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.mediator中介;


public abstract class Person {

    protected String name;
    protected Mediator mediator;

    public Person(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}
