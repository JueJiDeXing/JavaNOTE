package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.创建型.singleton单例.demo4;


public class Singleton {

    //私有构造方法
    private Singleton() {}

    //声明Singleton类型的变量
    private static volatile Singleton instance;

    //对外提供公共的访问方式
    public static Singleton getInstance() {
        //第一次判断，如果instance的值不为null，不需要抢占锁，直接返回对象
        if(instance == null) {
            synchronized (Singleton.class) {
                //第二次判断
                if(instance == null) {
                    instance = new Singleton();
                }
            }
        }

        return instance;
    }
}
