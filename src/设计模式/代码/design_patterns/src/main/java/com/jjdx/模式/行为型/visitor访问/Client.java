package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.visitor访问;


public class Client {
    public static void main(String[] args) {
        //创建Home对象
        Home home = new Home();
        //添加元素到Home对象中
        home.add(new Dog());
        home.add(new Cat());

        //创建主人对象
        Owner owner = new Owner();
        //让主人喂食所有的宠物
        home.action(owner);
    }
}
