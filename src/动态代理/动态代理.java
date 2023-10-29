package 动态代理;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Proxy;

public class 动态代理 {
    //无侵入式的给代码增加额外的功能
    public static void main(String[] args) {
        //创建代理Proxy对象
        BigStar cxk = new BigStar("cxk");
        StarP starP = ProxyUtil.createProxy(cxk);

        String result = starP.sing("鸡你太美");//通过代理调用方法,底层自动调用invoke方法

        starP.dance();
        System.out.println(result);

    }
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class BigStar implements StarP {
    String name;

    @Override
    public String sing(String name) {
        System.out.println(this.name + "正在唱" + name);
        return "谢谢";
    }

    @Override
    public String dance() {
        System.out.println(this.name + "正在跳");
        return "谢谢";
    }

}

interface StarP {
    String sing(String name);

    String dance();
}

class ProxyUtil {
    /**
     给明星对象创建一个代理

     @param bigStar 被代理的明星对象
     @return 给明星创建的代理
     */
    public static StarP createProxy(BigStar bigStar) {
        // public static Object newProxyInstance(
        // ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        // 参1:指定用哪个类加载器去加载生成的代理类
        // 参2:指定接口,这些接口用于指定生成的代理哪些方法
        // 参3:指定生成的代理对象在指定调用方法前要做哪些事情
        return (StarP) Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),//类加载器
                new Class[]{StarP.class},//接口
                (proxy, method, args) -> {//方法调用
                    //参1:代理的对象
                    //参2:要调用的方法
                    //参3:调用方法传递的实参
                    if ("sing".equals(method.getName())) {
                        System.out.println("准备话筒");
                    } else if ("dance".equals(method.getName())) {
                        System.out.println("准备场地");
                    }
                    return method.invoke(bigStar, args);//再去调用对象的方法
                });
    }
}
