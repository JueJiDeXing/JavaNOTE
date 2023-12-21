package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.bridge桥接;


public abstract class OpratingSystem {

    //声明videFile变量
    protected VideoFile videoFile;

    public OpratingSystem(VideoFile videoFile) {
        this.videoFile = videoFile;
    }

    public abstract void play(String fileName);
}
