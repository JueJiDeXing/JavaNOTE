package 基础.拓展.自动化;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 鼠标控制
 */
public class Controller {

    static Robot robot;

    public static boolean isIn() {
        for (int i = 0; i < 3; i++) {
            Point cur = getLocation();
            if (cur.x > 1300 && cur.y < 250) return false;
            Sleep.sleep(100);
        }
        return true;
    }

    public static Point getLocation() {
        return MouseInfo.getPointerInfo().getLocation();
    }

    static List<String[]> operators = new ArrayList<>();

    static {
        try {
            File file = new File(YamlReader.getString("controlFile"));
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                operators.add(line.split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     执行操作, 当鼠标在外部时停止

     @return 是否还需要执行
     */
    public static boolean operate() {
        for (String[] op : operators) {
            String type = op[0];
            switch (type) {
                case "move" -> move(Integer.parseInt(op[1]), Integer.parseInt(op[2]));
                case "keyPress" -> keyPress(Integer.parseInt(op[1]));
                case "mousePress" -> mousePress(Integer.parseInt(op[1]));
                default -> {
                    System.out.println("未知操作: " + type);
                }
            }
            Sleep.sleep(100);
            if (!isIn()) return false;
        }
        return true;
    }


    public static void mousePress(int mask) {
        robot.mousePress(mask);
        robot.mouseRelease(mask);
    }

    public static void move(int x, int y) {
        robot.mouseMove(x, y);
    }

    public static void keyPress(int key) {
        robot.keyPress(key);
        robot.keyRelease(key);
    }
}
