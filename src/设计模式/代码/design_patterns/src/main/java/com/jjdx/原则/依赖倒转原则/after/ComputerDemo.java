package 设计模式.代码.design_patterns.src.main.java.com.jjdx.原则.依赖倒转原则.after;

/**
 <h1>依赖倒转原则</h1>
 高层模块不应该依赖低层模块，两者都应该依赖其抽象；<br>
 抽象不应该依赖细节，细节应该依赖抽象。<br>
 简单的说就是要求对抽象进行编程，不要对实现进行编程，这样就降低了客户与实现模块间的耦合。<br>
 (类与类之间通过接口等耦合)
 */
public class ComputerDemo {
    public static void main(String[] args) {

        //创建计算机的组件对象
        HardDisk hardDisk = new XiJieHardDisk();
        Cpu cpu = new IntelCpu();
        Memory memory = new KingstonMemory();

        //创建计算机对象
        Computer c = new Computer();
        //组装计算机
        c.setCpu(cpu);
        c.setHardDisk(hardDisk);
        c.setMemory(memory);

        //运行计算机
        c.run();


        OtherCpu otherCpu = new OtherCpu();//OtherCpu实现Cpu接口
        c.setCpu(otherCpu);
    }
}
