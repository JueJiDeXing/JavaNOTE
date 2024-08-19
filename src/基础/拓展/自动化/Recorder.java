package 基础.拓展.自动化;

import java.awt.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 鼠标录制器
 */
public class Recorder {
    public static void main(String[] args) throws Exception {
        robot = new Robot();
        robot.delay(2000);
        robot.setAutoDelay(40);
        prepareRecord();
    }

    static Robot robot;
    static List<String> actions = new ArrayList<>();
    static String path_keyPress = "E:\\ideaProject\\NOTE\\src\\基础\\拓展\\自动化\\sounds\\1.mp3";
    static String path_mousePress = "E:\\ideaProject\\NOTE\\src\\基础\\拓展\\自动化\\sounds\\2.wav";
    static String path_Q = "E:\\ideaProject\\NOTE\\src\\基础\\拓展\\自动化\\sounds\\3.wav";
    static Listener listener;

    public static void prepareRecord() {
        listener = new Listener(startCode -> {
            // 按下Q开始录制
            String keyText = Listener.getKeyText(startCode);
            if (!keyText.equals("Q")) return;
            Voice.play(path_Q);
            startRecord();
        }, null);
    }

    private static void startRecord() {
        listener.setPressed(code -> {
            String text = Listener.getKeyText(code);
            if (text.equals("Q")) exit();// 按下Q关闭录制
            Voice.play(path_keyPress);
            String op = "press " + code;
            actions.add(op);
        }, code -> {
            Voice.play(path_mousePress);
            Point cur = Controller.getLocation();
            String op1 = "move " + cur.x + " " + cur.y;
            String op2 = "mousePress " + code;
            actions.add(op1);
            actions.add(op2);
        });
    }

    private static void exit() {
        Voice.play(path_Q);
        print();
        System.exit(0);
    }


    private static void print() {
        try (FileWriter writer = new FileWriter(
                "E:\\ideaProject\\NOTE\\src\\基础\\拓展\\自动化\\op2.txt")) {
            for (String action : actions) {
                writer.write(action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
