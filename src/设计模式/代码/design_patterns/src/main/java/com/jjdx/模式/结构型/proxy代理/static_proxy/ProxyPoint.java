package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.proxy代理.static_proxy;


public class ProxyPoint implements SellTickets {

    //声明火车站类对象
    private TrainStation trainStation  = new TrainStation();

    public void sell() {
        System.out.println("代售点收取一些服务费用");
        trainStation.sell();
    }

}
