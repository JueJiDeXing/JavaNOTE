package 基础.拓展.自动化;

import java.awt.*;

/**
 鼠标点击线程
 */
public class ExecThread extends Thread {
    Robot robot;

    ExecThread(Robot robot) {
        this.robot = robot;
    }

    public void run() {
        while (Controller.isIn()) {
            if (!Controller.operate()) break;
        }
        MyLogger.info("通过鼠标结束");
        System.exit(0);
    }

}
