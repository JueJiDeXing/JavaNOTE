package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.开闭原则;


public class SougouInput {

    private AbstractSkin skin;

    public void setSkin(AbstractSkin skin) {
        this.skin = skin;
    }

    public void display() {
        skin.display();
    }
}
