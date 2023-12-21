package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.adapter适配器.class_adapter;


public class SDAdapterTF extends TFCardImpl implements SDCard {

    public String readSD() {
        System.out.println("adapter适配器 read tf card");
        return readTF();
    }

    public void writeSD(String msg) {
        System.out.println("adapter适配器 write tf card");
        writeTF(msg);
    }
}
