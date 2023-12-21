package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.proxy代理.cglib_proxy;


public class Client {
    public static void main(String[] args) {
        //创建代理工厂对象
        ProxyFactory factory = new ProxyFactory();
        //获取代理对象
        TrainStation proxyObject = factory.getProxyObject();
        //调用代理对象中的sell方法卖票
        proxyObject.sell();
    }
}
