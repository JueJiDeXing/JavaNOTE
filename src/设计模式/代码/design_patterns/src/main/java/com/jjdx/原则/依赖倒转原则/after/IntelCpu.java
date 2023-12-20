package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.依赖倒转原则.after;

/**
 Intel处理器
 */
public class IntelCpu implements Cpu {

    public void run() {
        System.out.println("使用Intel处理器");
    }
}
