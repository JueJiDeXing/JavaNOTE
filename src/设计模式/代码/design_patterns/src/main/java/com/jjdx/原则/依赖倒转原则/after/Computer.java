package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.依赖倒转原则.after;


import lombok.Data;

@Data
public class Computer {

    private HardDisk hardDisk;
    private Cpu cpu;
    private Memory memory;

    //运行计算机
    public void run() {
        System.out.println("运行计算机");
        String data = hardDisk.get();
        System.out.println("从硬盘上获取的数据是：" + data);
        cpu.run();
        memory.save();
    }
}
