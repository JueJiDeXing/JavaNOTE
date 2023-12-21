package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.singleton单例.demo3;


public class Client {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();

        //判断两次获取到的Singleton对象是否是同一个对象
        System.out.println(instance == instance1);
    }
}
