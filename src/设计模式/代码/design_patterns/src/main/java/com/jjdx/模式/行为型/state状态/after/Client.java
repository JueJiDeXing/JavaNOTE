package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.行为型.state状态.after;


public class Client {
    public static void main(String[] args) {
        //创建环境角色对象
        Context context = new Context();
        //设置当前电梯装填
        context.setLiftState(new ClosingState());

        context.open();
        context.run();
        context.close();
        context.stop();
    }
}
