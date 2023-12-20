package 设计模式.代码.design_patterns.src.main.java.com.jjdx.模式.bridge;

/**
 * @version v1.0
 * @ClassName: VideoFile
 * @Description: 视频文件(实现化角色)
 * @Author: 黑马程序员
 */
public interface VideoFile {

    //解码功能
    void decode(String fileName);
}
