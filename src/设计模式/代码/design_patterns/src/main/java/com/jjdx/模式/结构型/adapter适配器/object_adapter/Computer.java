package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.adapter适配器.object_adapter;


public class Computer {

    //从SD卡中读取数据
    public String readSD(SDCard sdCard) {
        if(sdCard == null) {
            throw  new NullPointerException("sd card is not null");
        }
        return sdCard.readSD();
    }
}
