package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.adapter.object_adapter;

/**
 * @version v1.0
 * @ClassName: Computer
 * @Description: 计算机类
 * @Author: 黑马程序员
 */
public class Computer {

    //从SD卡中读取数据
    public String readSD(SDCard sdCard) {
        if(sdCard == null) {
            throw  new NullPointerException("sd card is not null");
        }
        return sdCard.readSD();
    }
}
