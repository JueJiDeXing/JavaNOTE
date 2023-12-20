package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.依赖倒转原则.after;

/**
 硬盘
 */
public interface HardDisk {

    //存储数据
    public void save(String data);

    //获取数据
    public String get();
}
