package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.singleton单例.demo6;


public class Client {
    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance1 = Singleton.INSTANCE;

        System.out.println(instance == instance1);
    }
}
