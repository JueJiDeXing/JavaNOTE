package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.bridge桥接;


public class Client {
    public static void main(String[] args) {
        //创建mac系统对象
        OpratingSystem system = new Mac(new AviFile());
        //使用操作系统播放视频文件
        system.play("战狼3");
    }
}
