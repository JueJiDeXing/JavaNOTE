package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.proxy代理.static_proxy;


public class Client {
    public static void main(String[] args) {
        //创建代售点类对象
        ProxyPoint proxyPoint = new ProxyPoint();
        //调用方法进行买票
        proxyPoint.sell();
    }
}
