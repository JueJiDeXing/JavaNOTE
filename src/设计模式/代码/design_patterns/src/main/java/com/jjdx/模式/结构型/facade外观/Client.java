package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.facade外观;


public class Client {
    public static void main(String[] args) {
        //创建智能音箱对象
        SmartAppliancesFacade facade = new SmartAppliancesFacade();
        //控制家电
        facade.say("打开家电");

        System.out.println("==================");

        facade.say("关闭家电");
    }
}
