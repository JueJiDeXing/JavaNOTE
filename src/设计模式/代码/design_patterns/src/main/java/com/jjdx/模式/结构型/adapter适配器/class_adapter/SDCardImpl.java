package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.adapter适配器.class_adapter;


public class SDCardImpl implements SDCard {

    public String readSD() {
        String msg = "SDCard read msg ： hello word SD";
        return msg;
    }

    public void writeSD(String msg) {
        System.out.println("SDCard write msg ：" + msg);
    }
}
