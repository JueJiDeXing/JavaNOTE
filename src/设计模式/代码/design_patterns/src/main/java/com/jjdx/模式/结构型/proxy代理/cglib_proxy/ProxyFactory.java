package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.proxy代理.cglib_proxy;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;


public class ProxyFactory implements MethodInterceptor {

    //声明火车站对象
    private final TrainStation station = new TrainStation();

    public TrainStation getProxyObject() {
        //创建Enhancer对象，类似于JDK代理中的Proxy类
        Enhancer enhancer = new Enhancer();
        //设置父类的字节码对象。指定父类
        enhancer.setSuperclass(TrainStation.class);
        //设置回调函数
        enhancer.setCallback(this);
        //创建代理对象
        TrainStation proxyObject = (TrainStation) enhancer.create();
        return proxyObject;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //System.out.println("方法执行了");
        System.out.println("代售点收取一定的服务费用(CGLib代理)");
        //要调用目标对象的方法
        Object obj = method.invoke(station, objects);
        return obj;
    }
}
