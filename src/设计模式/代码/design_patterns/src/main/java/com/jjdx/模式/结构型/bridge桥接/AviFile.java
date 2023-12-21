package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.bridge桥接;


public class AviFile implements VideoFile {

    public void decode(String fileName) {
        System.out.println("avi视频文件 ：" + fileName);
    }
}
