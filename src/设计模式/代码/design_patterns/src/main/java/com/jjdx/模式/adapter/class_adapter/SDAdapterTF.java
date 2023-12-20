package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.adapter.class_adapter;

/**
 * @version v1.0
 * @ClassName: SDAdapterTF
 * @Description: 适配器类
 * @Author: 黑马程序员
 */
public class SDAdapterTF extends TFCardImpl implements SDCard {

    public String readSD() {
        System.out.println("adapter read tf card");
        return readTF();
    }

    public void writeSD(String msg) {
        System.out.println("adapter write tf card");
        writeTF(msg);
    }
}
