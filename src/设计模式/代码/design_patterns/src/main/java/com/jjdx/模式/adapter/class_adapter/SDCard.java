package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.adapter.class_adapter;

/**
 * @version v1.0
 * @ClassName: SDCard
 * @Description: 目标接口
 * @Author: 黑马程序员
 */
public interface SDCard {

    //从SD卡中读取数据
    String readSD();
    //往SD卡中写数据
    void writeSD(String msg);
}
