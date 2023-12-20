package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.依赖倒转原则.before;


public class XiJieHardDisk {

    //存储数据的方法
    public void save(String data) {
        System.out.println("使用希捷硬盘存储数据为：" + data);
    }

    //获取数据的方法
    public String get() {
        System.out.println("使用希捷希捷硬盘取数据");
        return "数据";
    }
}
