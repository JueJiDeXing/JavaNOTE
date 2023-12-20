package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.依赖倒转原则.before;

/**
 没有抽取接口,Computer只能使用固定类型的组件
 */
public class ComputerDemo {
    public static void main(String[] args) {
        //创建组件对象
        XiJieHardDisk hardDisk = new XiJieHardDisk();
        IntelCpu cpu = new IntelCpu();
        KingstonMemory memory = new KingstonMemory();

        //创建计算机对象
        Computer c = new Computer();
        //组装计算机
        c.setCpu(cpu);
        c.setHardDisk(hardDisk);
        c.setMemory(memory);

        //运行计算机
        c.run();

        // ERROR
        // OtherCpu otherCpu=new OtherCpu();
        // c.setCpu(otherCpu);
    }
}
