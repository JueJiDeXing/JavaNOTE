package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.adapter.class_adapter;

/**
 * @version v1.0
 * @ClassName: SDCardImpl
 * @Description: 具体的SD卡
 * @Author: 黑马程序员
 */
public class SDCardImpl implements SDCard {

    public String readSD() {
        String msg = "SDCard read msg ： hello word SD";
        return msg;
    }

    public void writeSD(String msg) {
        System.out.println("SDCard write msg ：" + msg);
    }
}
