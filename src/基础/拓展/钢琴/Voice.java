package 基础.拓展.钢琴;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 音频播放器
 */
public class Voice {


    public static void play(String filePath) {
        play(new File(filePath));
    }

    public static void play(File audioFile) {
        try {
            // 创建音频输入流
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
            // 播放音频
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("播放 " + audioFile.getName() + " 失败: " + e.getMessage());
        }
    }
}
