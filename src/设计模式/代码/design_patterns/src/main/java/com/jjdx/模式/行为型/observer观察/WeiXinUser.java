package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.observer观察;


public class WeiXinUser implements Observer {

    private String name;

    public WeiXinUser(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + "-" + message);
    }
}
