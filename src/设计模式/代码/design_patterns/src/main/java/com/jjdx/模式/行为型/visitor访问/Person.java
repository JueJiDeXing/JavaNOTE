package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.visitor访问;


public interface Person {

    //喂食宠物狗
    void feed(Cat cat);
    //喂食宠物猫
    void feed(Dog dog);
}
