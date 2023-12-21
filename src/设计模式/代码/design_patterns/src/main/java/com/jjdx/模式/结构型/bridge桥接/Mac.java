package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.结构型.bridge桥接;


public class Mac extends OpratingSystem {

    public Mac(VideoFile videoFile) {
        super(videoFile);
    }

    public void play(String fileName) {
        videoFile.decode(fileName);
    }
}
