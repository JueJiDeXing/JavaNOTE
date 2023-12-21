package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.visitor访问;


public class Cat implements Animal {

    public void accept(Person person) {
        person.feed(this); //访问者给宠物猫喂食
        System.out.println("好好吃，喵喵喵。。。");
    }
}
