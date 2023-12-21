package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.visitor访问;


public class Someone implements Person {

    public void feed(Cat cat) {
        System.out.println("其他人喂食猫");
    }

    public void feed(Dog dog) {
        System.out.println("其他人喂食狗");
    }
}
