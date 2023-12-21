package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.adapter适配器.class_adapter;


public class TFCardImpl implements TFCard {

    public String readTF() {
        String msg = "TFCard read msg ： hello word TFcard";
        return msg;
    }

    public void writeTF(String msg) {
        System.out.println("TFCard write msg :" + msg);
    }
}
