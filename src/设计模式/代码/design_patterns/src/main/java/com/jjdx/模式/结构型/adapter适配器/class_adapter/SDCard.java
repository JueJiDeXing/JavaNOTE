package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.adapter适配器.class_adapter;


public interface SDCard {

    //从SD卡中读取数据
    String readSD();
    //往SD卡中写数据
    void writeSD(String msg);
}
