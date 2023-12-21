package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.observer观察;



public interface Subject {

    //添加订阅者（添加观察者对象）
    void attach(Observer observer);

    //删除订阅者
    void detach(Observer observer);

    //通知订阅者更新消息
    void notify(String message);
}
