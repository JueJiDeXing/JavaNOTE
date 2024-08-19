package 基础.拓展.自动化;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 音频播放器
 */
public class Voice {


    public static void play(String filePath) {
        if (!filePath.endsWith("wav")) {
            String[] split = filePath.split("\\.");
            split[split.length - 1] = "wav";
            filePath = String.join(".", split);
        }
        try {
            play(new File(filePath));
        } catch (Exception e) {
            System.out.println("播放 " + filePath + " 失败  -- " + e.getMessage());
        }
    }

    public static void play(File audioFile) throws Exception {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        Thread.sleep(300);
        clip.start();
        Thread.sleep(300);
    }
}
