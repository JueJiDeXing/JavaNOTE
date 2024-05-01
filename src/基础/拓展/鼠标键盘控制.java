package 基础.拓展;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class 鼠标键盘控制 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("输入运行时长(单位分钟): ");
        int runTime = sc.nextInt();
        try {
            System.out.println("初始化...");
            Robot robot = new Robot();
            robot.delay(3000);
            Point location;
            long time = System.currentTimeMillis();
            System.out.println("开始运行...");
            while (true) {
                long time1 = System.currentTimeMillis();
                long minute = (time1 - time) / 1000 / 60;
                if (minute > runTime) {
                    //关机
                    //Runtime.getRuntime().exec("shutdown -s -t 1");
                    break;
                }
                location = MouseInfo.getPointerInfo().getLocation();
                if (location.x > 1200 && location.y < 200) break;
                press(robot, KeyEvent.VK_ENTER);
                sleep(1000);
                location = MouseInfo.getPointerInfo().getLocation();
                if (location.x > 1200 && location.y < 200) break;
                press(robot, KeyEvent.VK_BACK_SPACE);
                sleep(1000);
                location = MouseInfo.getPointerInfo().getLocation();
                if (location.x > 1200 && location.y < 200) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("程序已结束");
    }

    private static void press(Robot robot, int key) {

        robot.keyPress(key);
        robot.keyRelease(key);
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
