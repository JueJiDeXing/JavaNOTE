package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.adapter适配器.object_adapter;


public class SDAdapterTF implements SDCard {

    //声明适配者类
    private TFCard tfCard;

    public SDAdapterTF(TFCard tfCard) {
        this.tfCard = tfCard;
    }

    public String readSD() {
        System.out.println("adapter适配器 read tf card");
        return tfCard.readTF();
    }

    public void writeSD(String msg) {
        System.out.println("adapter适配器 write tf card");
        tfCard.writeTF(msg);
    }
}
