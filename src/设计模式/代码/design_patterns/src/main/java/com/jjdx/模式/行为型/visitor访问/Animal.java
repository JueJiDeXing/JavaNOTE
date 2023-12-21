package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.visitor访问;


public interface Animal {

    //接受访问者访问的功能
    void accept(Person person);
}
