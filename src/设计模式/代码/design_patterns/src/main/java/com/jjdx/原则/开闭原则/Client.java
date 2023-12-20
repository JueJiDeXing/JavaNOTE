package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.开闭原则;

/**
 <h1>开闭原则</h1>
 <b>对扩展开放，对修改关闭</b><br>
 在程序需要进行拓展的时候，不能去修改原有的代码，实现一个热插拔的效果。<br>
 简言之，是为了使程序的扩展性好，易于维护和升级。<br>
 想要达到这样的效果，我们需要使用接口和抽象类。
 */
public class Client {
    public static void main(String[] args) {
        //1,创建搜狗输入法对象
        SougouInput input = new SougouInput();
        //2,创建皮肤对象
        //DefaultSkin skin = new DefaultSkin();
        MySkin skin = new MySkin();
        //3,将皮肤设置到输入法中
        input.setSkin(skin);

        //4,显示皮肤
        input.display();
    }
}
